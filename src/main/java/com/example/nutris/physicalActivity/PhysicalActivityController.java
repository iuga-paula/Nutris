package com.example.nutris.physicalActivity;

import com.example.nutris.errorMessage.ResponseMessage;
import com.example.nutris.food.Food;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/activity", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhysicalActivityController {
    private final PhysicalActivityService activityService;

    @Autowired
    public PhysicalActivityController(PhysicalActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> postActivity(@RequestBody @Valid PhysicalActivityDTO dto) {
        try {
            PhysicalActivityResponseDTO newActivity = activityService.addActivity(dto);
            return new ResponseEntity<>(new Gson().toJson(newActivity), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()).getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteActivity(@RequestParam(required = true) Long id) {
        try {
            activityService.deleteActivity(id);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()).getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage("Activity successfully deleted").getMessage(), HttpStatus.OK);
    }

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getActivity(@RequestParam(required = true) String startDate, @RequestParam(required = true) String endDate) throws ParseException {
        if(startDate.isEmpty() || endDate.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Please provide all params").getMessage(), HttpStatus.BAD_REQUEST) ;
        }
        System.out.print(startDate +' ' + endDate);
        List<PhysicalActivity> result = activityService.getActivityFromInterval(startDate, endDate);
        List<PhysicalActivityResponseDTO> response = new ArrayList<>();

        for (PhysicalActivity activity: result) {
            PhysicalActivityResponseDTO newActivity = new PhysicalActivityResponseDTO(activity);
            response.add(newActivity);

        }
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

}
