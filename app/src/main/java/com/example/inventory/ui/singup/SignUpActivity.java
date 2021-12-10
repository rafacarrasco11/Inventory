package com.example.inventory.ui.singup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventory.R;
import com.example.inventory.databinding.ActivitySignUpBinding;
import com.example.inventory.data.model.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private ActivitySignUpBinding binding;

    private SignUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /**
         * Se utiliza el metodo onBackPressed para eliminar la Activity SignUpActivity,
         * y restaurar la actividad anterior LoginActivity
         */
        //binding.btSignUp.setOnClickListener(view -> onBackPressed());

        presenter = new SignUpPresenter(this);

        // comienza el sign up
        binding.btSignUp.setOnClickListener(view -> {
            presenter.validateSignUp(
                    binding.tieUser.getText().toString(),
                    binding.tieEmail.getText().toString(),
                    binding.tietPassword.getText().toString(),
                    binding.tietConfirmPassword.getText().toString()
            );
        } );

        // la vista se registra como subscriptor de Event Bus
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();  // se evita memory leak
    }

    //Metodo que se ejecuta cuando hay un evento del repositorio
    @Subscribe
    public void onEvent(Event event){
        hideProgress();;
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }


    //region METODOS VIEW - PRESENTER

    @Override
    public void setUserEmptyError() {
        binding.tilEmail.setError(getString(R.string.errUserEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.errPasswordEmpty));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.errPassword));
    }

    @Override
    public void setPasswordEqualsError() {
        binding.tilConfirmPassword.setError(getString(R.string.errPasswordNotEquals));
    }

    @Override
    public void setEmailEror() {
        binding.tilEmail.setError(getString(R.string.errEmailPattern));
    }

    @Override
    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void showProgress() {
        binding.pBSignup.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pBSignup.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccess(String message) {
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    //endregion

}