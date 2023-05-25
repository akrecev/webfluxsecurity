package com.example.webfluxsecurity.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PBFDK2Encoder implements PasswordEncoder {

    private static final String SECRET_KEY_INSTANCE = "PBKDF2WithHmacSHA512";

    @Value("${jwt.password.encoder.secret}")
    private String secret;
    @Value("${jwt.password.encoder.iteration}")
    private Integer iteration;
    @Value("${jwt.password.encoder.keylength}")
    private Integer keyLength;

    @Override
    public String encode(CharSequence rawPassword) {

        try {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}
