package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;

public class DependencyListPresenter implements  DependencyListContract.Presenter, DependencyListContract.OnInteractorListener {
    private DependencyListContract.View view;
    private DependencyListInteractor interactor;
    private Boolean order = false;

    public DependencyListPresenter(DependencyListContract.View view) {
        this.view = view;
        interactor = new DependencyListInteractor(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void load(OnRepositoryListCallback callback) {
        view.showProgress();
        interactor.load(callback);
    }

    /**
     * Este metodo elimina una depend3encia de la BD/Servidor
     * @param dependency
     */
    @Override
    public void delete(Dependency dependency, OnRepositoryListCallback callback) {
        interactor.delete(dependency, callback);
    }

    @Override
    public void undo(Dependency dependency, OnRepositoryListCallback callback) {
        interactor.undo(dependency, callback);
    }

    @Override
    public void order() {
        if (order) {
            order = false;
            view.showDataInverseOrder();
        } else {
            order = true;
            view.showDataOrder();
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0)
            view.showNoData();
        else
            view.showData((ArrayList<Dependency>)list);

        view.hideProgress();
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.onDeleteSuccess(message);

    }

    @Override
    public void onUndoSuccess(String message) {
        view.onUndoSuccess(message);
    }
}
