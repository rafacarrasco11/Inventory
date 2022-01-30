package com.example.inventory.ui.section;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SectionListPresenter implements SectionListContract.Presenter, SectionListContract.OnInteractorListener {

    private SectionListContract.View view;
    private SectionListInteractor interactor;

    public SectionListPresenter(SectionListContract.View view) {
        this.view = view;
        this.interactor = new SectionListInteractor(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void load() throws ExecutionException, InterruptedException {
        interactor.load();
    }

    @Override
    public void delete(Section section) {
        interactor.delete(section);
    }

    @Override
    public void undo( Section section) {
        interactor.undo(section);
    }

    @Override
    public void order() {
        view.showDataOrder();
    }

    // -----
    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        if (list.size() == 0)
            view.showNoData();
        else
            view.showData((ArrayList<Section>)list);

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
