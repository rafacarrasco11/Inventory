package com.example.inventory.ui.section.manage;

import com.example.inventory.data.model.Section;

public class SectionManagePresenter implements SectionManageContract.Presenter, SectionManageContract.OnInteractorListener {

    private SectionManageContract.View view;
    private SectionManageInteractor interactor;

    public SectionManagePresenter(SectionManageContract.View view) {
        this.view = view;
        this.interactor = new SectionManageInteractor(this);
    }

    @Override
    public void onSuccess(String message) {
        view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        view.onFailure(message);
    }

    @Override
    public void onAddSuccess(String message) {
        view.onAddSuccess(message);
    }

    @Override
    public void onAddFailure(String message) {
        view.onAddFailure(message);
    }

    @Override
    public void onEditFailure(String message) {
        view.onEditFailure(message);
    }

    @Override
    public void onEditSuccess() {
        view.onEditSuccess();
    }

    @Override
    public void add(Section section) {
        interactor.add(section);
    }

    @Override
    public void edit(Section sectionEdit, Section section) {
        interactor.edit(sectionEdit,section);
    }
}
