package com.example.inventory.ui.section.manage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.data.model.Section;
import com.example.inventory.databinding.FragmentSectionManageBinding;

public class SectionManageFragment extends Fragment implements SectionManageContract.View {
    private FragmentSectionManageBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSectionManageBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onAddSuccess(String message) {

    }

    @Override
    public void onAddFailure(String message) {

    }

    @Override
    public void onEditFailure(String message) {

    }

    @Override
    public void onEditSuccess() {

    }

    @Override
    public void addDependency(Section section) {

    }

    @Override
    public void editDependency(Section sectionEdit, Section section) {

    }
}