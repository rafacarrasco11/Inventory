package com.example.inventory.ui.dependency.manage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyManageBinding;

public class DependencyManage extends Fragment {

    private FragmentDependencyManageBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dependency_manage, container, false);
        if (DependencyManageArgs.fromBundle(getArguments()).getDependency() != null) {
            // Editar
            getActivity().setTitle(getString(R.string.title_edit_ependency));

            initView(DependencyManageArgs.fromBundle(getArguments()).getDependency());

            initFabEdit();
        }
    }

    private void initFabEdit() {

    }

    private void initView(Dependency dependency) {
    }
}