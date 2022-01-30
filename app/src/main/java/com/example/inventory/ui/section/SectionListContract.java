package com.example.inventory.ui.section;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.ui.base.BasePresenter;
import com.example.inventory.ui.base.IProgressView;
import com.example.inventory.ui.base.OnRepositoryListCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SectionListContract {
    interface View extends OnRepositoryListCallback, IProgressView {
        void showData(List<Section> list);
        void showNoData();

        // A-Z
        void showDataOrder();
    }

    interface Presenter extends BasePresenter {
        void load() throws ExecutionException, InterruptedException;
        void delete (Section section);
        void undo(Section section);
        void order();
    }

    interface Repository {
        void getList(OnRepositoryListCallback callback) throws ExecutionException, InterruptedException;
        //2. cuando se realiza una pulsación larga se elimina
        void delete (Section section, OnRepositoryListCallback callback);
        //3. Cuando el usuario pulsa la opcion undo del snackbar
        void undo(Section section, OnRepositoryListCallback callback);
    }

    interface OnInteractorListener extends OnRepositoryListCallback {

    }
}
