package com.example.inventory.ui.base;

import java.util.List;

public interface OnRepositoryListCallback {
    void onFailure(String message);

    // El tipo es generico, en algun momento de la implementacion tendremos que
    // cambiar el T por en este caso, dependencias.
    <T>void onSuccess(List<T> List);

    //Todos lo repositorios lista contemplan los casos de delete y undo
    void onDeleteSuccess(String message);
    void onUndoSuccess(String message);
}
