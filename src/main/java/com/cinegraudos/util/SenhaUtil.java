package com.cinegraudos.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String createHash(String senha) {
        return encoder.encode(senha);
    }

    public static boolean verify(String senha, String senhaHash) {
        return encoder.matches(senha, senhaHash);
    }
}
