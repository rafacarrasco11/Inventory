package com.example.inventory.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.include.toolbar);
        setContentView(binding.getRoot());

        setHeaderRoundImage();

        // Personalizar navigation drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);

        // Inicializar el controlador de navegacion en la aplicacion
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Metodo que configura el componente Navigationview
        // OPCION 1: MOSTRAR SIEMPRE ICONO HAMBURGUESA
        //setupNavigationView();

        // OPCION2: MOSTRAR LOS NIVELES DE FRAGMENTS MEDIANTE LA FLECHA
        Set<Integer> topLevelDestination = new HashSet<>();
        topLevelDestination.add(R.id.addInventoryFragment);
        topLevelDestination.add(R.id.depndencyListFragment);
        topLevelDestination.add(R.id.aboutUsFragment);
        topLevelDestination.add(R.id.productFragment);
        topLevelDestination.add(R.id.sectionListFragment);
        //topLevelDestination.add(R.id.settingsFragment);

        // Configura la barra de accion para que funcione con NAVIGATIONUI
        // Este metodo gestiona el evento click del navigatioView y se mostrara el id del fragment
        // de navcontroller, SOLO SI, el id del item del menu es igual al id del FRAGMENT
        NavigationUI.setupWithNavController(binding.navigationView, navController);

        appBarConfiguration = new AppBarConfiguration.Builder(topLevelDestination).setOpenableLayout(binding.drawerLayout).build();

        // Con este metodo gestionamos la BARRA DE ACCION, cuando hay varios niveles de navegacion
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    private void setupNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addInventoryFragment:
                        showAddInventory();
                        break;
                    case R.id.depndencyListFragment:
                        showDependencies();
                        break;
                    case R.id.aboutUsFragment:
                        showAboutUs();
                        binding.navigationView.getCheckedItem().setChecked(false);
                        break;
                    case R.id.action_settings:
                        showSettings();
                        binding.navigationView.getCheckedItem().setChecked(false);
                        break;
                    case R.id.productFragment:
                        showProduct();
                        break;
                    case R.id.sectionListFragment:
                        showSection();
                        binding.navigationView.getCheckedItem().setChecked(false);
                        break;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }




    /**
     * Cuando se pulsa sobre el icono de la felcha debe ser el componente NAVIGATIONUI quien
     * gestione la navegacion hacia arriba
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.content_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(this, "Se ha pulsado buscar", Toast.LENGTH_LONG).show();
                return true;
            case R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                //Si los fragment modifican el menu de la activity se devuelve false

                return false;
        }
        //return super.onOptionsItemSelected(item);
    }

    private void setHeaderRoundImage() {
        ImageView img = ((ImageView)binding.navigationView.getHeaderView(0).findViewById(R.id.imvProfile_header));

        Drawable originalDrawable = getResources().getDrawable(R.drawable.ic_google);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        img.setImageDrawable(roundedDrawable);
    }


    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    /**
     * mostrar el fragment Add Inventory
     * @return
     */
    private void showAddInventory() {
        navController.navigate(R.id.addInventoryFragment);
    }

    /**
     * mostrar el fragment About Us
     * @return
     */
    private void showAboutUs() {
        navController.navigate(R.id.aboutUsFragment);
    }

    /**
     * mostrar el fragment Productos
     * @return
     */
    private void showProduct() {
        navController.navigate(R.id.productFragment);
    }

    /**
     * mostrar el fragment About Us
     * @return
     */
    private void showDependencies() {
        navController.navigate(R.id.depndencyListFragment);
    }

    /**
     * mostrar el fragment Settings
     * @return
     */
    private void showSettings() {
        //navController.navigate(R.id.);
    }

    /**
     * mostrar el fragment Section
     * @return
     */
    private void showSection() {
        navController.navigate(R.id.sectionListFragment);
    }
}