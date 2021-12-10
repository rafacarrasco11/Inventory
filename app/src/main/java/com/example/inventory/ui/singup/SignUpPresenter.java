package com.example.inventory.ui.singup;

import com.example.inventory.ui.login.LoginInteractor;

public class SignUpPresenter implements SignUpContract.Presenter, SignUpContract.OnInteractorListener {

    private SignUpContract.View view;
    private SignUpInteractor interactor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.interactor = new SignUpInteractor(this);

    }

    // METODOS PRESENTER VISTA
    @Override
    public void validateSignUp(String user, String email, String passwd, String corfmPasswd) {
        interactor.validateSignUp(user, email, passwd, corfmPasswd);
        view.showProgress();
    }

    //region METODOS INTERACTOR
    @Override
    public void onPasswordEmptyError() {
        view.hideProgress();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.hideProgress();
        view.setPasswordError();
    }

    @Override
    public void onUserEmptyError() {
        view.hideProgress();
        view.setUserEmptyError();
    }

    @Override
    public void onPasswordEqualsError() {
        view.hideProgress();
        view.setPasswordEqualsError();
    }

    @Override
    public void onEmailEmptyError() {
        view.hideProgress();
        view.setEmailEmptyError();
    }

    @Override
    public void onEmailError() {
        view.hideProgress();
        view.setEmailEror();
    }

    @Override
    public void onSuccess(String message) {
        view.hideProgress();
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.hideProgress();
        view.onFailure(message);
    }

    //endregion


    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }
}
