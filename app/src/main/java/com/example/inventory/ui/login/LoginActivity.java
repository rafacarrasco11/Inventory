package com.example.inventory.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inventory.ui.MainActivity;
import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.databinding.ActivityLoginBinding;
import com.example.inventory.ui.singup.SignUpActivity;
import com.example.inventory.utils.CommonUtils;
import com.example.inventory.utils.StateView;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    //Con esto evitamos implementar el loginPresenter completo
    //Así solo implementamos los métodos que haya en la interfaz
    // y la vista no tiene acceso completo a la clase loginPresenter
    //private LoginContract.Presenter presenter;

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Instanciar nuestra clase LoginnViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btSignUp.setOnClickListener(view -> {
            startSignUpActivity();
        });

        binding.btSingIn.setOnClickListener(view -> loginViewModel.validateCredentials());

        binding.setViewmodel(loginViewModel);

        // Se inicializa el listener que escucha los eventos
        binding.tietEmail.addTextChangedListener(new LoginTextMatcher(binding.tietEmail));
        binding.tietPassword.addTextChangedListener(new LoginTextMatcher(binding.tietPassword));

        // Se crea la vinculacion entre Owner LifeCycle y el Observador, dentro del LiveData que quiera observar
        loginViewModel.getError().observe(this, error -> {
            switch (error) {
                case R.string.errEmailEmpty:
                    setEmailEmptyError();
                    break;
                case R.string.errPasswordEmpty:
                    setPasswordEmptyError();
                    break;
                case R.string.errPassword:
                    setPasswordError();
                    break;
            }
        });
        loginViewModel.getState().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                StateView.State state = ((StateView) o).getState();
                switch (state) {
                    case LOADING:
                        showProgress();
                        break;
                    case ERROR:
                        hideProgress();
                        break;
                    case COMPLETE:
                        hideProgress();
                        break;
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //region Metodos del contrato View-Presenter

    /**
     * Este metodo activa el error en TextInputLayout. Mostrar el texto oportuno
     */
    public void setUserEmptyError() {
        binding.tilEmail.setError(getString(R.string.errUserEmpty));
    }

    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.errUserEmpty));
    }

    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.errPasswordEmpty));
    }

    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.errPassword));
    }

    /**
     * Secuencia normal: el usuario existe en la base de datos,
     * usuario y contraseña correctos
     */
    public void onSuccess(String message) {
        startMainActivity();
    }

    public void showProgress() {
        binding.loading.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        binding.loading.setVisibility(View.INVISIBLE);
    }

    //endregion

    private void startSignUpActivity() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    //region Clase interna que controla cada vez que el usuario introduce un caracter en un Editable
    //       si cumple o no las reglas de validacion

    class LoginTextMatcher implements TextWatcher {

        private View view;

        private LoginTextMatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tieEmail:
                    validateEmail(editable.toString());
                    break;
                case R.id.tietPassword:
                    validatePassword(editable.toString());
                    break;
            }
        }
    }

    /**
     * Metodo que valida la password mediante el metodo ya creado de la clase CommonsUtil
     * @param password
     */
    private void validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            binding.tilPassword.setError(getString(R.string.errUserEmpty));
        } else if (!CommonUtils.isPasswordValid(password)) {
            binding.tilPassword.setError(getString(R.string.errPassword));
        } else {
            // desaparece el error
            binding.tilPassword.setError(null);
        }
    }

    /**
     * Metodo que valida el Email
     * 1. no puede ser vacio
     * 2. vamos a utilizar el patron por defectod e EMAIL para comprobar que tiene formato correct
     * @param email
     */
    private void validateEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            binding.tilEmail.setError(getString(R.string.errUserEmpty));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.setError(getString(R.string.errEmailPattern));
        } else {
            // desaparece el error
            binding.tilEmail.setError(null);
        }
    }

    //endregion
}