package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public class DependencyListInteractor implements DependencyListContract.OnInteractorListener {

    private DependencyListContract.OnInteractorListener listener;

    public DependencyListInteractor(DependencyListContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess((ArrayList<Dependency>)list);
    }

    @Override
    public void onDeleteSuccess(String message) {
        listener.onDeleteSuccess(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }

    public void load(OnRepositoryListCallback callback) {
        //SIEMPRE SE ACCEDE DE FORMA ESTATICA AL REPOSITORIO
        DependencyRepository.getInstance().getList(callback);
    }

    /**
     * Elimina una dependencia del repositorio
     * @param dependency
     */
    public void delete(Dependency dependency, OnRepositoryListCallback callback) {
        DependencyRepository.getInstance().delete(dependency, callback);
    }

    public void undo(Dependency dependency, OnRepositoryListCallback callback) {
        DependencyRepository.getInstance().undo(dependency, callback);
    }
}
