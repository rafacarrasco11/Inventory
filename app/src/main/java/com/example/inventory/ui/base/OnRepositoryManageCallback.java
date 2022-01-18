package com.example.inventory.ui.base;

public interface OnRepositoryManageCallback extends OnRepositoryCallback{
    void onAddSuccess(String message);
    void onAddFailure(String message);

    void onEditFailure(String message);
    void onEditSuccess();
}
