package com.example.nutris.diet;

import com.example.nutris.food.Food;
import com.example.nutris.food.repository.FoodRepository;
import com.example.nutris.physicalActivity.PhysicalActivity;
import com.example.nutris.user.CustomUser;
import com.example.nutris.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final DietRepository dietRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public DietService(DietRepository dietRepository, UserRepository userRepository, FoodRepository foodRepository) {
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }


    public void deleteActivity(Long id) throws Exception {
        Optional<Diet> activity = dietRepository.findById(id);
        if(activity.isEmpty()) {
            throw new Exception("Diet not found");
        }
        dietRepository.delete(activity.get());
    }

    public DietResponseDTO addDiet(DietDTO dto) throws Exception {
        Date date = dateFormat.parse(dto.getDate());
        Long foodId = dto.getFoodId();
        Optional<Food> food = foodRepository.findById(foodId);
        if(food.isEmpty()) {
            throw new Exception("Specified food not found");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email  = (String) auth.getPrincipal();
        Optional<CustomUser> user = userRepository.findByEmail(email);
        Diet newDiet = new Diet(date, food.get(), user.get());
        dietRepository.save(newDiet);

        return new DietResponseDTO(newDiet);
    }

    public Float getCalories(String startDate, String endDate) throws ParseException {
       List<Diet> diets = dietRepository.findByDateBetween(dateFormat.parse(startDate), dateFormat.parse(endDate));
       Float sum = 0.0F;
        for (Diet diet : diets) {
            sum += diet.getFood().getCalories();
        }

        return sum;
    }
}
