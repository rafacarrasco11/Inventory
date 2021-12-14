package com.example.inventory.ui.dependency.manage;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.ui.base.OnRepositoryCallback;

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

    //REPO
    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
    }

    //
    public void add(Dependency d, OnRepositoryCallback callback) {
        DependencyRepository.getInstance().add(d,callback);
    }

    public void edit(Dependency d, OnRepositoryCallback callback) {
        DependencyRepository.getInstance().add(d,callback);
    }
}
