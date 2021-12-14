package com.example.inventory.ui.dependency.manage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyManageBinding;
import com.example.inventory.ui.dependency.DependencyListFragmentDirections;
import com.google.android.material.snackbar.Snackbar;

public class DependencyManage extends Fragment implements DependencyManageContract.View {

    private FragmentDependencyManageBinding binding;
    private DependencyManagePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new DependencyManagePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDependencyManageBinding.inflate(inflater,container,false);

        if (DependencyManageArgs.fromBundle(getArguments()).getDependency() != null ) {
            // Editar
            getActivity().setTitle(getString(R.string.title_edit_ependency));

            initView(DependencyManageArgs.fromBundle(getArguments()).getDependency());

            initFabEdit();
        }
        else {
            initFabAdd();
        }

        return binding.getRoot();
    }

    private void initFabAdd() {
        binding.fab.setOnClickListener( v -> { presenter.add(getDependency(), this); });
    }

    private void initFabEdit() {
        binding.fab.setImageResource(R.drawable.ic_baseline_edit_24);
        binding.tieDependencyShortName.setEnabled(false);
        binding.fab.setOnClickListener( v -> { presenter.edit(getDependency(), this); });
    }

    /**
     * Dada una dependencia, inicializa la vista
     * @param dependency
     */
    private void initView(Dependency dependency) {
        binding.tieDependencyShortName.setText(dependency.getShortName());
        binding.tieDependencyShortName.setEnabled(true);
        binding.tieDependencyName.setText(dependency.getName());
        binding.tieDependencyDescription.setText(dependency.getDescription());
        binding.tieDependencyImage.setText(dependency.getImage());
    }
        /*
        Dada una vista, se recoge la dependencia que el usuario a intreoducido o modificado
         */

    private Dependency getDependency() {
        Dependency d = new Dependency();
        d.setShortName(binding.tilDependencyShortName.getEditText().toString());
        d.setName(binding.tilDependencyName.getEditText().toString());
        d.setDescription(binding.tilDependencyDescription.getEditText().toString());
        d.setImage(binding.tilDependencyImage.getEditText().toString());
        return d;
    }

    //region METODOS VISTA
    @Override
    public void addDependency(Dependency d) {
        presenter.add(d,this);
    }

    @Override
    public void editDependency(Dependency d) {
        presenter.edit(d,this);
    }
    //endregion


    //region METODOS REPO CALLBACK
    @Override
    public void onSuccess(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAddSuccess(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_LONG).show();
    }
    //endregion
}
