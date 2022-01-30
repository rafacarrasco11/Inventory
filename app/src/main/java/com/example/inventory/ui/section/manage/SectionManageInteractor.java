package com.example.inventory.ui.section.manage;

import com.example.inventory.data.model.Section;
import com.example.inventory.data.repository.SectionRepository;
import com.example.inventory.ui.base.OnRepositoryManageCallback;

public class SectionManageInteractor implements OnRepositoryManageCallback {

    private SectionManageContract.OnInteractorListener listener;

    public SectionManageInteractor(SectionManageContract.OnInteractorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccess(String message) {
        listener.onSuccess(message);
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

    public void add(Section section) {
        SectionRepository.getInstance().add(section, this);
    }

    public void edit(Section sectionEdit, Section section) {
        SectionRepository.getInstance().edit(sectionEdit, section, this);
    }
}
