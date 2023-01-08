package com.example.nutris.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomUserConfig {
    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {
        return args -> {
            CustomUser newUser = new CustomUser("Maria", "Popovici", "mariap@gmail.com", "1210201201");
            newUser.changeRole(UserRole.NUTRIONIST);
            userRepository.saveAndFlush(newUser);
        };
    }
}
