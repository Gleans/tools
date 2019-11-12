package com.example.demo.oauthuaa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPasswd {
        public static void main(String[] args) {
            System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
