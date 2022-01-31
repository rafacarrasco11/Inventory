package com.example.inventory.ui.section.manage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventory.InventoryApplication;
import com.example.inventory.R;
import com.example.inventory.data.dao.DependencyDAO;
import com.example.inventory.data.database.InventoryDatabase;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.data.model.Section;
import com.example.inventory.databinding.FragmentSectionManageBinding;
import com.example.inventory.ui.dependency.DependencyAdapter;
import com.example.inventory.ui.section.SectionAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class SectionManageFragment extends Fragment implements SectionManageContract.View, DependencyAdapter.OnManagerDependencyListener {

    private FragmentSectionManageBinding binding;
    private SectionManageContract.Presenter presenter;
    private DependencyAdapter adapter;
    private List<Dependency> list;
    private DependencyDAO dependencyDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new SectionManagePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSectionManageBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvSection();
        if (SectionManageFragmentArgs.fromBundle(getArguments()).getSection() != null ) {
            Section sectionEdit = SectionManageFragmentArgs.fromBundle(getArguments()).getSection();
            // Editar
            getActivity().setTitle(getString(R.string.title_edit_section));

            initView(sectionEdit);

            initFabEdit(sectionEdit);
        }
        else {
            initFabAdd();
        }

        list = new ArrayList<>();
        dependencyDAO = InventoryDatabase.getDatabase().dependencyDAO();
        try {
            // Obtener las dependencias de la base de datos
            list = InventoryDatabase.databaseWriteExecutor.submit(() -> dependencyDAO.select()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Actualizar la lista
        adapter.updateList(list);
    }

    private void initFabAdd() {
        binding.fab.setOnClickListener( v -> { presenter.add(getSection()); });
    }

    private void initFabEdit(Section section) {
        binding.fab.setImageResource(R.drawable.ic_baseline_edit_24);
        binding.fab.setOnClickListener( v -> { presenter.edit(section, getSection()); });
    }

    private Section getSection() {
        Section s = new Section();

        // Solo si estamos en editar se recoge el ID, en caso contrario se deja que ROOM autogener un id
        if (SectionManageFragmentArgs.fromBundle(getArguments()).getSection() != null )
            s.setId(SectionManageFragmentArgs.fromBundle(getArguments()).getSection().getId());

        s.setShortName(binding.tilSectionShortname.getEditText().getText().toString());
        s.setName(binding.tilSectionName.getEditText().getText().toString());
        s.setDescription(binding.tilSectionDescription.getEditText().getText().toString());
        s.setDependency(Long.parseLong(binding.tvDependencyID.getText().toString()));
        return s;
    }

    private void initView(Section section ) {
        binding.tieSectionShortname.setText(section.getShortName());
        binding.tieSectionShortname.setEnabled(true);
        binding.tieSectionName.setText(section.getName());
        binding.tieSectionDescription.setText(section.getDescription());
    }

    @Override
    public void onSuccess(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onAddSuccess(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        bundle.putSerializable(Section.TAG, getSection());

        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.sectionManageFragment)
                .setArguments(bundle)
                .createPendingIntent();

        //5.Crear la notificacion
        Notification.Builder builder = new Notification.Builder(getActivity(), InventoryApplication.IDCHANNEL)
                .setSmallIcon(R.drawable.ic_baseline_edit_24)
                .setAutoCancel(true)
                .setContentTitle(getResources().getString(R.string.notification_title_add_section))
                .setContentText(String.format("Se ha añadido la seccion ", getSection().getName()))
                .setContentIntent(pendingIntent);

        //6. Añadir la notificacion al manager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());

        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onAddFailure(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEditFailure(String message) {
        Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onEditSuccess() {
        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void addSection(Section section) {
        presenter.add(section);
    }

    @Override
    public void editSection(Section sectionEdit, Section section) {
        presenter.edit(sectionEdit,section);
    }

    @Override
    public void onEditDependency(Dependency dependency) {
        binding.tvDependencyID.setText(Integer.toString(dependency.getId()));
        binding.tvDependencyName.setText(dependency.getShortName());
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {

    }

    @Override
    public void onSelectDependency(Dependency dependency) {

    }

    //------- RV Dependencias
    private void initRvSection() {
        adapter = new DependencyAdapter(new ArrayList<>(), this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvDependecyInSection.setLayoutManager(linearLayoutManager);
        binding.rvDependecyInSection.setAdapter(adapter);
    }
}