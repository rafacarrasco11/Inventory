package com.example.inventory.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Entity
public class User {

    @Ignore
    public static final String TAG = "User";
    @NonNull
    private String user;
    @NonNull
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}