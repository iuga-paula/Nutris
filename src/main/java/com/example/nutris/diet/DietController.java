package com.example.nutris.diet;


import com.example.nutris.errorMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/v1/diet", produces = MediaType.APPLICATION_JSON_VALUE)
public class DietController {

    public final DietService dietService;

    @Autowired
    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCalories(@RequestParam(required = true) String startDate, @RequestParam(required = true) String endDate) {
        if (startDate.isEmpty() || endDate.isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Please provide all params").getMessage(), HttpStatus.BAD_REQUEST);
        }
        try {
            Float calories = dietService.getCalories(startDate, endDate);
            return new ResponseEntity<>(new ResponseMessage(calories + " calories").getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()).getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> postDiet(@RequestBody @Valid DietDTO dto) {
        try {
            DietResponseDTO newDiet = dietService.addDiet(dto);
            return new ResponseEntity<>(newDiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()).getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteDiet(@RequestParam(required = true) Long id) {
        try {
            dietService.deleteActivity(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()).getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage("Diet successfully deleted").getMessage(), HttpStatus.OK);
    }
}
