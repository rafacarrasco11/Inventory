package com.example.inventory.ui.section;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.data.model.Section;
import com.example.inventory.databinding.FragmentSectionBinding;
import com.example.inventory.ui.dependency.DependencyListFragmentDirections;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SectionListFragment extends Fragment implements SectionListContract.View, SectionAdapter.OnManagerSectionListener {

    private FragmentSectionBinding binding;
    private SectionAdapter adapter;
    private SectionListContract.Presenter presenter;
    // Para eliminar
    private Section sectionDeleted;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(v -> {
            SectionListFragmentDirections.ActionSectionListFragmentToSectionManageFragment action
                    = SectionListFragmentDirections.actionSectionListFragmentToSectionManageFragment(null);

            NavHostFragment.findNavController(this).navigate(action);
        });
        initRvSection();
    }

    private void initRvSection() {
        adapter = new SectionAdapter(new ArrayList<>(), this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvSection.setLayoutManager(linearLayoutManager);
        binding.rvSection.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSectionBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        presenter = new SectionListPresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            presenter.load();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sectionlist_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_orderdependency:
                //Toast.makeText(getActivity(), "Se ha pulsado ordenarDependencias", Toast.LENGTH_LONG).show();
                presenter.order();
                return true;
            case R.id.action_orderbydescrip_dependency:
                adapter.order();
            default:
                //Si los fragment modifican el menu de la activity se devuelve falso
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        showData((List<Section>) list);
    }

    @Override
    public void onDeleteSuccess(String message) {
        Snackbar.make(getView(),message, BaseTransientBottomBar.LENGTH_SHORT).setAction(getString(R.string.undo), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.undo(sectionDeleted);
                binding.rvSection.setVisibility(View.VISIBLE);
                binding.llNoSections.setVisibility(View.GONE);
            }
        }).show();

        adapter.delete(sectionDeleted);
        if (adapter.getItemCount() == 0) {
            showNoData();
        }
    }

    @Override
    public void onUndoSuccess(String message) {
        adapter.undo(sectionDeleted);
        if (binding.llNoSections.getVisibility()==View.VISIBLE){
            binding.llNoSections.setVisibility(View.GONE);
            binding.rvSection.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showData(List<Section> list) {
        adapter.updateList(list);
        binding.rvSection.setVisibility(View.VISIBLE);
        binding.llNoSections.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {
        binding.rvSection.setVisibility(View.GONE);
        binding.llNoSections.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDataOrder() {
        adapter.order();
    }

    @Override
    public void onEditSection(Section section) {
        Toast.makeText(getActivity(), "Editando la seccion " + section.getName(), Toast.LENGTH_SHORT).show();
        // NO FUNCIONA SectionListFragmentDirections
        SectionListFragmentDirections.ActionSectionListFragmentToSectionManageFragment action
                = SectionListFragmentDirections.actionSectionListFragmentToSectionManageFragment(section);

        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDeleteSection(Section section) {
        sectionDeleted = section;
        presenter.delete(section);
    }
}