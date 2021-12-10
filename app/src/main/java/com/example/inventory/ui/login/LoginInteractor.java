package com.example.inventory.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventory.data.model.User;
import com.example.inventory.data.repository.login.LoginRepositoryImpl;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.utils.CommonUtils;

public class LoginInteractor implements OnRepositoryCallback {

    private LoginContract.OnInteractorListener listener;
    private LoginContract.Repository repository;

    // El persentador que quiera utilizar esta clase debe implementar
    // esta siguiente interfaz

    public LoginInteractor(LoginContract.OnInteractorListener listener) {
        this.listener = listener;
        this.repository = LoginRepositoryImpl.getInstance(this);
    }

    public void validateCredentials(User user){
        //En base a lo que suceda avisará a su Listener/Presentador
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //A gestionar las alternativas del caso de uso
                if(TextUtils.isEmpty(user.getUser())) {
                    listener.onUserEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(user.getPassword())){
                    listener.onPasswordEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(user.getPassword()))
                {
                    listener.onPasswordError();
                    return;
                }
                repository.login(user);

            }
        }, 2000);
    }

    // Estos metodos vienen de la respuesta que nos de el Repositorio

    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }
}
