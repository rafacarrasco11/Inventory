package com.example.inventory.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inventory.R;
import com.example.inventory.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    /**
     * Vamos a simular un timepo de espera con un hilo que duerme 2 segundos y cuando despierta
     * se ejecutara un metodo startLogin() que inicia la activity Login.
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLogin();
            }
        },WAIT_TIME);
    }

    private void startLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        // Voy a llamar de forma explicita al metodo finish() de una Activity, para eliminar
        // esta Activity de la pila de actividades, porque si el usuario pulsa BACK, no
        // queremos que la visualize
        finish();
    }
}