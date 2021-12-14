package com.example.inventory.ui.dependency;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.IProgressView;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.List;

public interface DependencyListContract {

    /**
     * Esta interfaz tiene los siguientes metodos:
     *  - respuesta del repositorio
     *  - metodos necesarios para mostrar un progreso
     *  - metodos necesarios para gestopnar los datos de un RecyclerView
     */
    interface View extends OnRepositoryListCallback, IProgressView {
        void showData(List<Dependency> list);
        void showNoData();

        // A-Z
        void showDataOrder();
        // Z-A
        void showDataInverseOrder();
    }

    /**
     * Interfaz que debe implementar el presenter
     */
    interface Presenter extends BasePresenter{
        //1. Cargar los datos
        void load(OnRepositoryListCallback callback);
        //2. cuando se realiza una pulsación larga se elimina
        void delete (Dependency dependency, OnRepositoryListCallback callback);
        //3. Cuando el usuario pulsa la opcion undo del snackbar
        void undo(Dependency dependency, OnRepositoryListCallback callback);
        // 4. Que la lista se ordene por nombre
        void order();
    }
    /**
     * Interfaz que debe implementar toda la clase que quiera ser un Repositorio
     * Lista
     */
    interface Repository{
        //1. Cargar los datos
        void getList(OnRepositoryListCallback callback);
        //2. cuando se realiza una pulsación larga se elimina
        void delete (Dependency dependency, OnRepositoryListCallback callback);
        //3. Cuando el usuario pulsa la opcion undo del snackbar
        void undo(Dependency dependency, OnRepositoryListCallback callback);
    }

    /**
     * Interfaz que debe implementar el Listener del Interactor
     * Esta interfaz muestra las posibles alernativas de los casos de uso
     * - LISTAR ELEMENTOS (getList)
     * - ELIMINAR (delete)
     * - DESHACER (Undo)
     */
    interface OnInteractorListener extends OnRepositoryListCallback{

    }


}
