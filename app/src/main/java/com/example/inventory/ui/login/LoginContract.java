package com.example.inventory.ui.login;
import android.icu.lang.UCharacter;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.IProgressView;
import com.example.inventory.ui.base.OnRepositoryCallback;

/**
 * Esta interfaz es el contrato estre la vista y el presentador del login
 */
public interface LoginContract {

    /**
     * Interfaz que debe imlpementar mi vista
     */
    interface View extends OnRepositoryCallback, IProgressView {

        //Alternativas del caso de uso, set porque se modidica elementos de la vista
        void setUserEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();

        // No es necesario ya que incorporamos la interfaz iprogressview
        //Contrato de la vista y presentador
        void showProgress();
        void hideProgress();
    }

    /**
     * Interfaz que debe imlpementar el presenter
     */
    interface Presenter extends BasePresenter {
        void validateCredentials(User user);
    }

    /**
     * Interfaz que debe implementar toda clase que quiera ser un Repositorio para el login
     */
    interface Repository {
        void login(User user);
    }

    /**
     * Interfaz que debe implementar el Listener del interactor
     * Esta INTERFAZ es las posibles alternativas del caso de uso LOGIN
     */
    interface OnInteractorListener extends OnRepositoryCallback {
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onPasswordError();
    }

    /**
     * Interfaz que debe implementar toda clase que este relacionada con la
     * respuesta del Repositorio (Accion Login)
     * Esta INTERFAZ es la SECUENCIA NORMAL de LOGIN
     */
    // Sustituido por repositorycalllback
/*    interface  OnLoginListener {
        void onSuccess(String message);
        void onFailure(String message);
    }*/
}
