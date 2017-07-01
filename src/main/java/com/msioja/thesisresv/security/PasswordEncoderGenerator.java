package com.msioja.thesisresv.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {
    public static String generate(String password) {
        String hashedPass = "";
        for (int i = 0; i < 10; i++) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPass = passwordEncoder.encode(password);
        }
        return hashedPass;
    }
}
