package com.example.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.login.LoginRepositoryImpl;
import com.example.inventory.utils.CommonUtils;
import com.example.inventory.utils.StateView;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<User> user;
    private StateView state;
    private MutableLiveData<Integer> error;
    private MutableLiveData<String> email;
    private MutableLiveData<String> passwd;

    public LoginViewModel() {
        state = new StateView();
        user = new MutableLiveData<>();
        error = new MutableLiveData<>();
        email = new MutableLiveData<>();
        passwd = new MutableLiveData<>();
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public StateView getState() {
        return state;
    }

    public MutableLiveData<Integer> getError() {
        return error;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPasswd() {
        return passwd;
    }

    public void validateCredentials() {
        // Estado de la vista pasa a LOADING
        state.setState(StateView.State.LOADING);

        //A gestionar las alternativas del caso de uso
        if (TextUtils.isEmpty(email.getValue())) {
            error.setValue(R.string.errEmailEmpty);
            return;
        }
        if (TextUtils.isEmpty(passwd.getValue())) {
            error.setValue(R.string.errPasswordEmpty);
            return;
        }
        if (!CommonUtils.isPasswordValid(passwd.getValue())) {
            error.setValue(R.string.errPassword);
            return;
        }
        // En este caso hacemos la llamada al Repositorio y se modifican el LiveData<User>
        //user.postValue()
        user.setValue(LoginRepositoryImpl.getInstance().login(new User(email.getValue(),passwd.getValue())));

        state.setState(StateView.State.COMPLETE);
    }
}
