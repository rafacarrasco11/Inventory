package com.example.inventory.data.repository.signup;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventory.data.repository.login.LoginRepositoryImpl;
import com.example.inventory.ui.base.OnRepositoryCallback;
import com.example.inventory.ui.login.LoginContract;
import com.example.inventory.ui.singup.SignUpContract;
import com.example.inventory.ui.singup.SignUpInteractor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpRepositoryImpl implements SignUpContract.Repository {

    private static final String TAG = SignUpRepositoryImpl.class.getName();

    private static SignUpRepositoryImpl instance;
    private OnRepositoryCallback listener;

    public static SignUpRepositoryImpl getInstance(OnRepositoryCallback listener){
        if (instance == null) {
            instance = new SignUpRepositoryImpl();
        }

        instance.listener = listener;

        return instance;
    }

    @Override
    public void signUp(String email, String passwd) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email,passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmailAndPassword:success");
                            listener.onSuccess("usuario creado");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());

                            listener.onFailure("Error de autenticacion" + task.getException());
                        }
                    }
                });
    }
}
