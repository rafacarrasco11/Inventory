package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;

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
    public void load() {
        view.showProgress();
        interactor.load();
    }

    /**
     * Este metodo elimina una depend3encia de la BD/Servidor
     * @param dependency
     */
    @Override
    public void delete(Dependency dependency) {
        interactor.delete(dependency);
    }

    @Override
    public void undo(Dependency dependency) {
        interactor.undo(dependency);
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
