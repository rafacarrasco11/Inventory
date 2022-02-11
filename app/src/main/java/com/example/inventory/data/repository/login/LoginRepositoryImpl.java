package com.example.inventory.data.repository.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.data.model.Event;
import com.example.inventory.data.model.User;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

public class LoginRepositoryImpl {

    private static final String TAG = LoginRepositoryImpl.class.getName();

    private static LoginRepositoryImpl instance;
    private OnRepositoryCallback listener;

    public static LoginRepositoryImpl getInstance(OnRepositoryCallback listener){
        if (instance == null) {
            instance = new LoginRepositoryImpl();
        }

        instance.listener = listener;

        return instance;
    }


    public void login(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(user.getUser(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            listener.onSuccess("usuario correcto");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());

                            //listener.onFailure("Error de autenticacion" + task.getException());

                            // Se crea un Evento mediante EventBus
                            Event loginEvent = new Event();
                            loginEvent.setEventType(Event.onLoginError);
                            loginEvent.setMessage(task.getException().toString());

                            // Publica un evento mediante el metodo post()
                            EventBus.getDefault().post(loginEvent);
                        }
                    }
                });
    }
}
