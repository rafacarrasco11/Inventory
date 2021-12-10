package com.example.inventory.utils;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase no se podra heredar de ella
 * mediante el modificador final
 */
public final class CommonUtils {

    static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)(?=.*[ !\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,20}$";

    // Metodos Static
    /**
     * Metodo que comprueba que la contrasena es valida:
     * - Debe contener al menos un digito
     * - Debe contener al menos un caracter mayuscula
     * - Debe contener al menos un caracter minuscula
     * - Debe contener al menos un caracter especial
     * - Debe tener como minimo 8 caracteres y max 20
     */
    public static boolean isPasswordValid(String password) {
        Pattern pattern =Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
