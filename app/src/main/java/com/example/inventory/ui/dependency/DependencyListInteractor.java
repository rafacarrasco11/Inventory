package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;

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

    public void load() {
        //SIEMPRE SE ACCEDE DE FORMA ESTATICA AL REPOSITORIO
        DependencyRepository.getInstance(this).getList();
    }

    /**
     * Elimina una dependencia del repositorio
     * @param dependency
     */
    public void delete(Dependency dependency) {
        DependencyRepository.getInstance(this).delete(dependency);
    }

    public void undo(Dependency dependency) {
        DependencyRepository.getInstance(this).undo(dependency);
    }
}
