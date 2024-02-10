package com.springboot.books.springbootbooksrestapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//Simple class demonstrating the usage of BCryptPasswordEncoder to encode passwords.
public class PasswordEncoderImpl {

    public static void main(String[] args) {
        // Creating a BCryptPasswordEncoder instance
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Encoding the password for an admin user
        String encodedAdminPassword = passwordEncoder.encode("admin");
        System.out.println("Encoded admin password: " + encodedAdminPassword);

        // Encoding the password for a regular user
        String encodedUserPassword = passwordEncoder.encode("user");
        System.out.println("Encoded user password: " + encodedUserPassword);
    }
}