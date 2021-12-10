package com.example.inventory.data.repository.login;

import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.login.LoginContract;

import java.util.ArrayList;

/**
 * Vamos a simular que la instancia de LoginRepository es unica. Si es asi
 * PATRON SINGLLETON
 * -- el primer requisito es que el constructor es privado
 * -- todas las clases obtienen la instancia a traves de un metod que se llama getInstance()
 */
public class LoginRepositoryStatic implements LoginContract.Repository {

    private static LoginRepositoryStatic instance;
    private OnRepositoryCallback listener;

    //Lista de usuarios autorizados en mi app
    private ArrayList<User> users;

    private LoginRepositoryStatic() {
        this.users = new ArrayList<>();
        initialize();
    }

    /**
     * Inicializa la estructura de datos de uan clase (ArrayList)
     */
    private void initialize() {
        users.add(new User("rafacarrascotm@gmail.com","123Rafa123!"));
    }

    public static LoginRepositoryStatic getInstance(OnRepositoryCallback listener){
        if (instance == null) {
            instance = new LoginRepositoryStatic();
        }

        instance.listener = listener;

        return instance;
    }


    /**
     * Este es el metodo que comprueba si el usuario existe no, Hay que recorrer el ArrayList
     * @param u
     */
    @Override
    public void login(User u) {
        for (User user: this.users) {
            if (user.getUser().equals(u.getUser()) && user.getPassword().equals(u.getPassword())) {
                listener.onSuccess("Usuario correcto");
                return;
            }
        }

        // En caso contrario, NO EXISTE
        listener.onFailure("Error en la autenticacion");
    }
}
