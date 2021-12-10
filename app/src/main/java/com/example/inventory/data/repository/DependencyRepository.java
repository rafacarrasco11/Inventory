package com.example.inventory.data.repository;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.example.inventory.ui.dependency.DependencyListContract;

import java.util.ArrayList;
import java.util.Collections;

public class DependencyRepository implements DependencyListContract.Repository {

    private static DependencyRepository instance;
    private OnRepositoryListCallback callback;
    private ArrayList<Dependency> list;

    private DependencyRepository(){
        list = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        //list.add(new Dependency("AAula 1º GSDAM","1GSDAM","A",""));
      //  list.add(new Dependency("BAula 2º GSDAM","1GSDAM","D",""));
      //  list.add(new Dependency("CAula 1º GSDAM","2GSDAM","C",""));
        //list.add(new Dependency("AAula 2º GSDAM","2GSDAM","B",""));
        list.add(new Dependency("DAula 3º GSDAM","2GSDAM","A",""));
    }


    public static DependencyRepository getInstance(OnRepositoryListCallback callback){
        if (instance == null){
            instance = new DependencyRepository();
        }
        instance.callback = callback;
        return instance;
    }


    @Override
    public void getList() {
        Collections.sort(list);
        callback.onSuccess(list);
    }

    @Override
    public void delete(Dependency dependency) {
        list.remove(dependency);
        callback.onDeleteSuccess("Se ha eliminado la dependencia " + dependency.getShortName());
    }

    @Override
    public void undo(Dependency dependency) {
        list.add(dependency);
        callback.onUndoSuccess("Dependencia " + dependency.getShortName() + " recuperada");
    }
}
