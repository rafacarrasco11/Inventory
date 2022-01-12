package com.example.inventory.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.inventory.R;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.MainActivity;
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
        if(!saveSession()) {
            new Handler().postDelayed(() -> startLogin(), WAIT_TIME);
        } else{
            new Handler().postDelayed(() -> startApp(), WAIT_TIME);
        }
    }

    /**
     * Metodo que comprueba si el usuario ha iniciado sesion y se ha guardado un email
     * en el fichero de preferencias DefaultSharedPreferences
     * @return
     */
    private boolean saveSession() {
        return (PreferenceManager.getDefaultSharedPreferences(this).contains(User.TAG));
    }

    private void startLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        // Voy a llamar de forma explicita al metodo finish() de una Activity, para eliminar
        // esta Activity de la pila de actividades, porque si el usuario pulsa BACK, no
        // queremos que la visualize
        finish();
    }

    private void startApp(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        //Voy a llamar de forma explicita al metodo finisg de una activity para eliminar
        //esta activity de la pila de actividades. porque si el usuario pulsa BACK
        //no queremos que se visualice
        finish();
    }
}