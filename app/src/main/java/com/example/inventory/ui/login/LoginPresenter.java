package com.example.inventory.ui.login;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.BasePresenter;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnInteractorListener, BasePresenter {

    private LoginContract.View view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new LoginInteractor(this);
    }

    //Metodos del contrato presenter-vista
    @Override
    public void validateCredentials(User user) {
        interactor.validateCredentials(user);
        view.showProgress();
    }


    //region Metodos del contrato presenter-interactor
    @Override
    public void onUserEmptyError() {
        view.hideProgress();
        view.setUserEmptyError();
    }

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
