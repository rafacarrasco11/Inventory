package com.example.inventory.ui.dependency.manage;

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

import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inventory.InventoryApplication;
import com.example.inventory.R;
import com.example.inventory.data.model.Dependency;
import com.example.inventory.databinding.FragmentDependencyManageBinding;
import com.example.inventory.ui.dependency.DependencyListFragmentDirections;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

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
        binding = FragmentDependencyManageBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (DependencyManageArgs.fromBundle(getArguments()).getDependency() != null ) {
            Dependency dEdit = DependencyManageArgs.fromBundle(getArguments()).getDependency();
            // Editar
            getActivity().setTitle(getString(R.string.title_edit_ependency));

            initView(dEdit);

            initFabEdit(dEdit);
        }
        else {
            initFabAdd();
        }
    }

    private void initFabAdd() {
        binding.fab.setOnClickListener( v -> { presenter.add(getDependency(), this); });
    }

    private void initFabEdit(Dependency dEdit) {
        binding.fab.setImageResource(R.drawable.ic_baseline_edit_24);
        binding.tieDependencyShortName.setEnabled(false);
        binding.fab.setOnClickListener( v -> { presenter.edit(dEdit, getDependency(), this); });
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
        d.setShortName(binding.tilDependencyShortName.getEditText().getText().toString());
        d.setName(binding.tilDependencyName.getEditText().getText().toString());
        d.setDescription(binding.tilDependencyDescription.getEditText().getText().toString());
        d.setImage(binding.tilDependencyImage.getEditText().getText().toString());
        return d;
    }

    //region METODOS VISTA
    @Override
    public void addDependency(Dependency d) {
        presenter.add(d,this);
    }

    @Override
    public void editDependency(Dependency dEdit, Dependency d) {
        presenter.edit(dEdit, d,this);
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
        //Crear la notificacion, pero antes se tiene que

        //1. Crear un bundle, añadir la Dependencia
        Bundle bundle = new Bundle();
        bundle.putSerializable(Dependency.TAG, getDependency());

        //2. Crear el intent(ESTO ES EN EL CASO DE TRABAJAR CON ACTIVIDADES)
        //Intent intent = new Intent(getActivity(), SplashActivity.class);
        //intent.putExtras(bundle);

        //3.Crear el PendingIntent que contiene el Intent
        //PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), new Random().nextInt(1000), intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //4. Si se utiliza el componente Navigation, se utiliza el grafo de navegacion
        //ERROR DIFICIL DE DETECTAR Y ES, EL TAG DEÑ BUNDLE SE DEBE LLAMAR IGUAL QUE EL ARGUMENTO
        //QUE SE HA ESTABLECIDO EN SAFE ARGS, que crea automaticamente un metodo segun el nombre del argumento
        //Dependency.TAG = dependency
        //Y el metodo de SAFE ARGS es getDependency()
        PendingIntent pendingIntent = new NavDeepLinkBuilder(getActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.dependencyManage)
                .setArguments(bundle)
                .createPendingIntent();

        //5.Crear la notificacion
        Notification.Builder builder = new Notification.Builder(getActivity(), InventoryApplication.IDCHANNEL)
                .setSmallIcon(R.drawable.ic_baseline_edit_24)
                .setAutoCancel(true)
                .setContentTitle(getResources().getString(R.string.notification_title_add_dependency))
                .setContentText(String.format("Se ha añadido la dependencia ", getDependency().getName()))
                .setContentIntent(pendingIntent);

        //6. Añadir la notificacion al manager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(new Random().nextInt(), builder.build());

        NavHostFragment.findNavController(this).navigateUp();
    }

    @Override
    public void onAddFailure(String message) {
        Toast.makeText(getActivity(), "Fallo al añadir dependencia", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditFailure(String message) {
        Toast.makeText(getActivity(), "Fallo al editar la dependencia", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditSuccess() {
        NavHostFragment.findNavController(this).navigateUp();
    }
    //endregion
}
