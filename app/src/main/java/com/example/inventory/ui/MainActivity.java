package com.example.inventory.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.include.toolbar);
        setContentView(binding.getRoot());

        setHeaderRoundImage();

        // Personalizar navigation drawer
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializar el controlador de navegacion en la aplicacion
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Metodo que configura el componente Navigationview
        setupNavigationView();
    }

    private void setupNavigationView() {
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_inventory:
                        showAddInventory();
                        break;
                    case R.id.action_dependency:
                        showDependencies();
                        break;
                    case R.id.action_aboutus:
                        showAboutUs();
                        binding.navigationView.getCheckedItem().setChecked(false);
                        break;
                    case R.id.action_settings:
                        showSettings();
                        binding.navigationView.getCheckedItem().setChecked(false);
                        break;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
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
        // return super.onOptionsItemSelected(item);
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
}