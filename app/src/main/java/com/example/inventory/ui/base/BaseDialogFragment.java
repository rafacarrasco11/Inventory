package com.example.inventory.ui.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String REQUEST = "requestdialog";
    public static final String KEY_BUNDLE="result";
    private static final String TAG = "BSAEDIALOGFRAGMENT";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG,"FUERA");
        if (getArguments() != null) {
            Log.d(TAG,"DENTRO");
            String title = getArguments().getString(TITLE);
            String message = getArguments().getString(MESSAGE);
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();
                }
            });
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Una de las claves para realizar la comunicación entre fragmentos (padre-hijo) es utilizar los métodos
                    // supportFragmentManager de la actividad para realizar el intercambio de información.
                    Bundle result = new Bundle();
                    result.putBoolean(KEY_BUNDLE, true);
                    getActivity().getSupportFragmentManager().setFragmentResult(REQUEST, result);
                }
            });

            return builder.create();
        }
        return null;
    }
}