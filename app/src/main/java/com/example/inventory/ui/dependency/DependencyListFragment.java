package com.example.inventory.ui.dependency;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
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
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.ActivityMainBinding;
import com.example.inventory.databinding.FragmentDependencyListBinding;
import com.example.inventory.ui.base.BaseDialogFragment;
import com.example.inventory.ui.base.OnRepositoryListCallback;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DependencyListFragment extends Fragment implements DependencyListContract.View, DependencyAdapter.OnManagerDependencyListener {

    private FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
    private DependencyListContract.Presenter presenter;
    private OnRepositoryListCallback callback;
    // Una vez que el repositorio elimina la dependencia, el adapter debe eliminar la dependencia
    private Dependency deleted;

    private RecyclerView mRecyclerView;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependency();
        initFab();
    }

    private void initFab() {
        binding.fab.setOnClickListener(v -> {
            DependencyListFragmentDirections.ActionDepndencyListFragmentToDependencyManage action
                    = DependencyListFragmentDirections.actionDepndencyListFragmentToDependencyManage(null);

            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    /**
     * Este metodo inicializa el componente recycler view
     */
    private void initRvDependency() {
        // 1. Inicializar el adapter
        adapter = new DependencyAdapter(new ArrayList<>(),this);

        //2. OBLIGATORIAMENTE se debe indicar que diseno (layout) rendra el recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        //3. Adigno el layout al recyclerview
        binding.rvDependency.setLayoutManager(linearLayoutManager);

        //4. Asigna a la vista sus datos (mdoelo)
        binding.rvDependency.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1. Se debe indicar a la activity que se quiere modificar el menu
        setHasOptionsMenu(true);

        presenter = new DependencyListPresenter(this);
        callback = this;
    }

    // 2. Sobreescribir/anular el metodo onCreateoptionsMenu para anadir el menu del fragment
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragmentlist_menu, menu);
    }

    //3. Implementar las acciones especificas(item) del menu del fragment
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_orderdependency:
                //Toast.makeText(getActivity(), "Se ha pulsado ordenarDependencias", Toast.LENGTH_LONG).show();
                presenter.order();
                return true;
            case R.id.action_orderbydescrip_dependency:
                adapter.orderByDescription();
            default:
                //Si los fragment modifican el menu de la activity se devuelve falso
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_dependency_list, container, false);
        binding = FragmentDependencyListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Pide y solicita lls datos
        presenter.load(this);
    }

    //region METODOS VIEW
    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public <T> void onSuccess(List<T> list) {
        showData((List<Dependency>) list);
    }

    /**
     * Metod que muestra un snackbar con la opcion UNDO
     * @param message
     */
    @Override
    public void onDeleteSuccess(String message) {
        Snackbar.make(getView(),message, BaseTransientBottomBar.LENGTH_SHORT).setAction(getString(R.string.undo), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.undo(deleted, callback);
                binding.rvDependency.setVisibility(View.VISIBLE);
                binding.llDependecyListShowNoData.setVisibility(View.GONE);
            }
        }).show();
        adapter.delete(deleted);
        if (adapter.getItemCount() == 0) {
            showNoData();
        }
    }

    @Override
    public void onUndoSuccess(String message) {
        adapter.undo(deleted);
        if (binding.llDependecyListShowNoData.getVisibility()==View.VISIBLE){
            binding.llDependecyListShowNoData.setVisibility(View.GONE);
            binding.rvDependency.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showData(List<Dependency> list) {
        adapter.updateList(list);
        binding.rvDependency.setVisibility(View.VISIBLE);
        binding.llDependecyListShowNoData.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {
        binding.llDependecyListShowNoData.setVisibility(View.VISIBLE);
        binding.rvDependency.setVisibility(View.GONE);
    }

    @Override
    public void showDataOrder() {
        adapter.order();
    }

    @Override
    public void showDataInverseOrder() {
        adapter.inverseOrder();
    }

    @Override
    public void onEditDependency(Dependency dependency) {
        Toast.makeText(getActivity(), "Editando la dependencia " + dependency.getName(), Toast.LENGTH_SHORT).show();
        DependencyListFragmentDirections.ActionDepndencyListFragmentToDependencyManage action
                = DependencyListFragmentDirections.actionDepndencyListFragmentToDependencyManage(dependency);

        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, String.format(getString(R.string.title_delete_dependency),dependency.getShortName()));
        bundle.putString(BaseDialogFragment.MESSAGE, String.format(getString(R.string.message_delete_dependency),dependency.getShortName()));
        NavHostFragment.findNavController(DependencyListFragment.this).navigate(R.id.action_depndencyListFragment_to_baseDialogFragment, bundle);

        //Una de las claves para realizar la comunicación entre fragmentos (padre-hijo) es utilizar los métodos supportFragmentManager
        // de la actividad para realizar el intercambio de información.
        // MUUUUUUUUUYYYY IMPORTANTE: si se usa la libreria de soporte se debe llamar a SETSUPPORTFRAGMENTMANAGER
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                //Si la respuesta es true en deletedDependency se procede con el caso de uso DELETE
                if (bundle.getBoolean(BaseDialogFragment.KEY_BUNDLE)) {
                    deleted = dependency;
                    presenter.delete(dependency,callback);

                }
            }
        });
    }

    @Override
    public void onSelectDependency(Dependency dependency) {

    }

    //endregion

}