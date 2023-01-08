package com.example.nutris.food;

import com.example.nutris.food.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodConfig {
    @Bean
    CommandLineRunner commandLineRunner(FoodRepository foodRepository) {
        return args -> {
            Food apple = new Food("apple", 95F, 25F, 1F, 0F, 19F, 3F, 86F, 0F, 0F, 7F, 0F, 0F, 0F, 0F, 0F, 1F, "Cardiovascular disease, Type 2 diabetes, Weight management, Cancer", "digestive issues, sensitive teeth");
            foodRepository.saveAndFlush(apple);
        };
    }
}
