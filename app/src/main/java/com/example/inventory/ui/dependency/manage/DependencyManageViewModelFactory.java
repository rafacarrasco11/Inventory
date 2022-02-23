package com.example.inventory.ui.dependency.manage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.inventory.data.model.Dependency;

public class DependencyManageViewModelFactory implements ViewModelProvider.Factory {

    private Dependency mParam;
    MutableLiveData<Boolean> editSuccess;
    MutableLiveData<Boolean> addSuccess;

    public DependencyManageViewModelFactory(Dependency param) {
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DependencyManageViewModel(mParam);
    }

}
