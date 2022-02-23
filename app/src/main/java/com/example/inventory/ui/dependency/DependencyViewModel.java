package com.example.inventory.ui.dependency;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.repository.DependencyRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DependencyViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Dependency>> dependencies;
    private MutableLiveData<Integer> size;
    private MutableLiveData<Boolean> empty;
    private MutableLiveData<Dependency.Order> order;

    public DependencyViewModel() {
        dependencies = new MutableLiveData<ArrayList<Dependency>>(DependencyRepository.getInstance().getList());
        size = new MutableLiveData<>(dependencies.getValue().size());
        empty = new MutableLiveData<>(dependencies.getValue().isEmpty());
        order = new MutableLiveData<>(Dependency.Order.SHORTNAME);
    }

    public MutableLiveData<ArrayList<Dependency>> getDependencies() {
        return dependencies;
    }

    public void setDependencies(MutableLiveData<ArrayList<Dependency>> dependencies) {
        this.dependencies = dependencies;
    }

    public MutableLiveData<Integer> getSize() {
        return size;
    }

    public void setSize(MutableLiveData<Integer> size) {
        this.size = size;
    }

    public MutableLiveData<Boolean> getEmpty() {
        return empty;
    }

    public void setEmpty(MutableLiveData<Boolean> empty) {
        this.empty = empty;
    }

    public MutableLiveData<Dependency.Order> getOrder() {
        return order;
    }

    public void setOrder(MutableLiveData<Dependency.Order> order) {
        this.order = order;
    }
}
