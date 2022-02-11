package com.example.inventory.data.repository.signup;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.singup.SignUpContract;

import java.util.ArrayList;

public class SignUpRepositoryStatic implements SignUpContract.Repository {
    private static SignUpRepositoryStatic instance;
    private OnRepositoryCallback listener;

    //Lista de usuarios autorizados en mi app
    private ArrayList<User> users;

    private SignUpRepositoryStatic() {
        this.users = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        users.add(new User("rafacarrascotm@gmail.com","123Rafa123!"));
    }

    public static SignUpRepositoryStatic getInstance(OnRepositoryCallback listener){
        if (instance == null) {
            instance = new SignUpRepositoryStatic();
        }

        instance.listener = listener;

        return instance;
    }


    @Override
    public void signUp(String email, String passwd) {
        for (User userTmp : this.users) {
            if (userTmp.getUser().equals(email)) {
                listener.onFailure("Usuario existe");
                return;
            }
        }

        // En caso contrario, NO EXISTE
        listener.onSuccess("Usuario no existe, se ha registrado");
    }
}
