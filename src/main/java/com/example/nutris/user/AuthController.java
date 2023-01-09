package com.example.nutris.user;

import com.example.nutris.errorMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam(required = true) String email,
                                              @RequestParam(required = true) String password) {
        try {
            return authService.authenticateUser(email, password);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("User with provided email not found").getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam(required = true) String firstName,
                                          @RequestParam(required = true) String lastName,
                                          @RequestParam(required = true) String email,
                                          @RequestParam(required = true) String password) {
        return authService.registerUser(firstName, lastName, email, password);

    }
}