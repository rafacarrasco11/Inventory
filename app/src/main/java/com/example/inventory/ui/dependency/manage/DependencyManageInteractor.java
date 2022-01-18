package com.example.inventory.ui.dependency.manage;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryManageCallback;

public class DependencyManageInteractor implements DependencyManageContract.OnInteractorListener{

    private DependencyManageContract.OnInteractorListener listener;

    public DependencyManageInteractor(DependencyManageContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }

    @Override
    public void onAddSuccess(String message) {
        listener.onAddSuccess(message);
    }

    @Override
    public void onAddFailure(String message) {
        listener.onAddFailure(message);
    }

    @Override
    public void onEditFailure(String message) {
        listener.onEditFailure(message);
    }

    @Override
    public void onEditSuccess() {
        listener.onEditSuccess();
    }

    //REPO
    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    //
    public void add(Dependency d, OnRepositoryManageCallback callback) {
        DependencyRepository.getInstance().add(d,callback);
    }

    public void edit(Dependency dEdit, Dependency d, OnRepositoryManageCallback callback) {
        DependencyRepository.getInstance().edit(dEdit, d,callback);
    }
}
