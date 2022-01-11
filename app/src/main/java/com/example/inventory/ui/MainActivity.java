package com.example.inventory.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.GravityCompat;

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

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

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

}