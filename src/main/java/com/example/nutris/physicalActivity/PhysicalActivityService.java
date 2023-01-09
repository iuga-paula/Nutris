package com.example.nutris.physicalActivity;

import com.example.nutris.user.CustomUser;
import com.example.nutris.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service

public class PhysicalActivityService {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final PhysicalActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhysicalActivityService(PhysicalActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public PhysicalActivityResponseDTO addActivity(PhysicalActivityDTO dto) throws Exception {
        PhysicalActivity newActivity = new PhysicalActivity();
        newActivity.setName(dto.getName());
        newActivity.setCaloriesBurnt(dto.getCaloriesBurnt());
        newActivity.setDuration(dto.getDuration());
        newActivity.setDate(new SimpleDateFormat("dd.MM.yyyy").parse(dto.getDate()));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email  = (String) auth.getPrincipal();
        Optional<CustomUser> user = userRepository.findByEmail(email);
        newActivity.setUser(user.get());

        activityRepository.save(newActivity);

        return new PhysicalActivityResponseDTO(newActivity);
    }

    public void deleteActivity(Long id) throws Exception {
        Optional<PhysicalActivity> activity = activityRepository.findById(id);
        if(activity.isEmpty()) {
            throw new Exception("Activity not found");
        }
        activityRepository.delete(activity.get());
    }

    public List<PhysicalActivity> getActivityFromInterval(String startDate, String endDate) throws ParseException {
        return activityRepository.findByDateBetween(new SimpleDateFormat("dd.MM.yyyy").parse(startDate), new SimpleDateFormat("dd.MM.yyyy").parse(endDate));
    }
}
