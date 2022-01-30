package com.example.inventory;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

import com.example.inventory.data.database.InventoryDatabase;

public class InventoryApplication extends Application {
    public static final String IDCHANNEL = "345767";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        InventoryDatabase.create(this);
    }

    private void createNotificationChannel() {
        //Se crea una clase Notificationchannel, es necesario que la API sea 26 o mas,
        //porque no se ha incluido en la librería de soporte
        //Si tenemos el minSdk 26 podemos quitar el if

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //1. Definir la importancia
            int importace = NotificationManager.IMPORTANCE_DEFAULT;
            //2. Definir el nombre del canal
            String nameChannel = getString(R.string.name_channel);
            //3. Se crea el canal
            NotificationChannel notificationChannel = new NotificationChannel(IDCHANNEL, nameChannel, importace);

            //3.1. OPCIONAL, configurar el modo de luces, sonido, vibración
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);

            //4. Añadir este canal a NotificationManager
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }
    }
}
