package com.example.inventory.ui.section;

import com.example.inventory.data.database.InventoryDatabase;
import com.example.inventory.data.model.Section;
import com.example.inventory.data.repository.SectionRepository;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SectionListInteractor implements OnRepositoryListCallback {

    private SectionListContract.OnInteractorListener listener;

    public SectionListInteractor(SectionListContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    public void load() throws ExecutionException, InterruptedException {
        SectionRepository.getInstance().getList(this);
    }

    public void delete(Section section) {
        SectionRepository.getInstance().delete(section,this);
    }

    public void undo(Section section) {
        SectionRepository.getInstance().undo(section, this);
    }

    @Override
    public void onFailure(String message) {
        listener.onFailure(message);
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        listener.onSuccess(list);
    }

    @Override
    public void onDeleteSuccess(String message) {
        listener.onDeleteSuccess(message);
    }

    @Override
    public void onUndoSuccess(String message) {
        listener.onUndoSuccess(message);
    }
}
