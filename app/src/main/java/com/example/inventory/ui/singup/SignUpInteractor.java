package com.example.inventory.ui.singup;

import android.text.TextUtils;

import com.example.inventory.data.repository.login.LoginRepositoryImpl;
import com.example.inventory.data.repository.signup.SignUpRepositoryImpl;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.utils.CommonUtils;

public class SignUpInteractor implements OnRepositoryCallback {

    private SignUpContract.OnInteractorListener listener;
    private SignUpContract.Repository repository;

    public SignUpInteractor(SignUpContract.OnInteractorListener listener) {
        this.listener = listener;
        this.repository = SignUpRepositoryImpl.getInstance(listener);
    }
    
    public void validateSignUp(String user, String email, String passwd, String corfmPasswd) {
        if(TextUtils.isEmpty(passwd)) {
            listener.onPasswordEmptyError();
            return;
        }
        if (!CommonUtils.isPasswordValid(passwd))
        {
            listener.onPasswordError();
            return;
        }
        if(TextUtils.isEmpty(user)) {
            listener.onUserEmptyError();
            return;
        }
        if (!passwd.equals(corfmPasswd)){
            listener.onPasswordEqualsError();
            return;
        }
        if (TextUtils.isEmpty(email))
        {
            listener.onEmailEmptyError();
            return;
        }
        if (!CommonUtils.isEmailValid(email))
        {
            listener.onEmailError();
            return;
        }

        repository.signUp(email,passwd);
    }
    

    //region METODOS RESPUESTA REPOSITORIO
    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
    
    //endregion
}
