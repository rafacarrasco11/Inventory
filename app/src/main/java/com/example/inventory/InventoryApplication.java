package com.example.inventory;

import android.app.Application;

public class InventoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
    }
}
