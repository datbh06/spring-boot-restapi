package com.yugen.springbootrestapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Utility class for generating encoded passwords.
 */
public class PasswordGeneratorEncoder {

    /**
     * The main method that generates encoded passwords.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Print the encoded password for "admin"
        System.out.println(passwordEncoder.encode("admin"));

        // Print the encoded password for "password"
        System.out.println(passwordEncoder.encode("password"));
    }
}
