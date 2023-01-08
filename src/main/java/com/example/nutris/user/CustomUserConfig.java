package com.example.nutris.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomUserConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {

        return args -> {
            CustomUser newUser = new CustomUser("Maria", "Popovici", "mariap@gmail.com",  passwordEncoder.encode("1210201201"));
            newUser.changeRole(UserRole.NUTRIONIST);
            userRepository.saveAndFlush(newUser);
        };
    }
}
