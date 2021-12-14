package com.example.inventory.ui.dependency.manage;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.base.OnRepositoryManageCallback;

public interface DependencyManageContract {

    interface View extends OnInteractorListener {
        void addDependency(Dependency d);
        void editDependency(Dependency d);
    }

    interface Presenter {
        void add(Dependency d, OnRepositoryCallback callback);
        void edit(Dependency d, OnRepositoryCallback callback);
    }

    interface Repository {
        void add(Dependency d, OnRepositoryCallback callback);
        void edit(Dependency d, OnRepositoryCallback callback);
    }

    interface OnInteractorListener extends OnRepositoryManageCallback {

    }

}
