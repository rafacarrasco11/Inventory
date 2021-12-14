package com.example.inventory.ui.dependency.manage;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryCallback;

public class DependencyManagePresenter implements DependencyManageContract.Presenter, DependencyManageContract.OnInteractorListener {

    private DependencyManageInteractor interactor;
    private DependencyManageContract.View view;

    public DependencyManagePresenter (DependencyManageContract.View view) {
        this.view = view;
        this.interactor = new DependencyManageInteractor(this);
    }


    @Override
    public void add(Dependency d, OnRepositoryCallback callback) {
        interactor.add(d, callback);
        callback.onSuccess("Se ha anadido la dependencia " + d.getShortName());
    }

    @Override
    public void edit(Dependency d, OnRepositoryCallback callback) {
        interactor.edit(d, callback);
        callback.onSuccess("Se ha editado la dependencia " + d.getShortName());
    }

    //
    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onAddSuccess(String message) {
        view.onAddSuccess(message);
    }

    //
    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }
}
