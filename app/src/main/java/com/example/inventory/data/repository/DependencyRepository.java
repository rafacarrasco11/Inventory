package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.dependency.DependencyListContract;
import com.example.inventory.ui.dependency.manage.DependencyManageContract;

import java.util.ArrayList;
import java.util.Collections;

public class DependencyRepository implements DependencyListContract.Repository, DependencyManageContract.Repository {

    private static DependencyRepository instance;
    private OnRepositoryListCallback callback;
    private ArrayList<Dependency> list;

    private DependencyRepository(){
        list = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        //list.add(new Dependency("AAula 1º GSDAM","1GSDAM","A",""));
        list.add(new Dependency("BAula 2º GSDAM","1GSDAM","D",""));
        list.add(new Dependency("CAula 1º GSDAM","2GSDAM","C",""));
        list.add(new Dependency("AAula 2º GSDAM","2GSDAM","B",""));
        list.add(new Dependency("DAula 3º GSDAM","2GSDAM","A",""));
    }

    @Override
    public void getList(OnRepositoryListCallback callback) {
        callback.onSuccess(list);
    }

    @Override
    public void delete(Dependency dependency, OnRepositoryListCallback callback) {
        list.remove(dependency);

        callback.onDeleteSuccess("Se ha eliminado la dependencia: " + dependency.getName());
    }

    @Override
    public void undo(Dependency dependency, OnRepositoryListCallback callback) {
        list.add(dependency);

        callback.onUndoSuccess("Se ha recuperado la dependencia: " + dependency.getName());
    }

    @Override
    public void add(Dependency d, OnRepositoryCallback callback) {
        list.add(d);
        callback.onSuccess("Dependencia " + d.getShortName() + " anadida");
    }

    @Override
    public void edit(Dependency d, OnRepositoryCallback callback) {

    }

    public static DependencyRepository getInstance(){
        if (instance == null){
            instance = new DependencyRepository();
        }

        return instance;
    }

}
