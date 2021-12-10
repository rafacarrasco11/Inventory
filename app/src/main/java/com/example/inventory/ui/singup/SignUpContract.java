package com.example.inventory.ui.singup;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.IProgressView;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.login.LoginContract;

/**
 * Esta interfaz es el contrato estre la vista y el presentador del login
 */
public interface SignUpContract {

    /**
     * Interfaz que debe imlpementar mi vista
     */
    interface View extends LoginContract.View, OnRepositoryCallback, IProgressView {

        //Extendemos del view del login ya que tenemos que implementar los mismos
        //metodos, e indicamos los que necesitamos a parte

        //Alternativas del caso de uso, set porque se modidica elementos de la vista
        //void setUserEmptyError();
        //void setPasswordEmptyError();
        //void setPasswordError();
        void setPasswordEqualsError();
        void setEmailEror();
        void setEmailEmptyError();

        //Contrato de la vista y presentador
        //void showProgressBar();
        //void hideProgressBar();

    }

    /**
     * Interfaz que debe imlpementar el presenter
     */
    interface Presenter extends BasePresenter {
        void validateSignUp(String user, String email, String passwd, String corfmPasswd);
    }

    /**
     * Interfaz que debe implementar toda clase que quiera ser un Repositorio para el login
     */
    interface Repository {
        void signUp(String email, String passwd );
    }

    /**
     * Interfaz que debe implementar el Listener del interactor
     * Esta INTERFAZ es las posibles alternativas del caso de uso LOGIN
     */
    interface OnInteractorListener extends OnRepositoryCallback {
        void onPasswordEmptyError();
        void onPasswordError();
        void onUserEmptyError();
        void onPasswordEqualsError();
        void onEmailEmptyError();
        void onEmailError();
    }

    /**
     * Interfaz que debe implementar toda clase que este relacionada con la
     * respuesta del Repositorio (Accion Login)
     * Esta INTERFAZ es la SECUENCIA NORMAL de LOGIN
     */
    // se ha sustituido por la clas onrepositrycallback
    /*interface OnSignUpListener {
        void onSuccess(String message);
        void onFailure(String message);
    }*/

}
