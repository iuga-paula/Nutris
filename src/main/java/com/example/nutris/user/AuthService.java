package com.example.nutris.user;

import com.example.nutris.errorMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> registerUser (String firstName, String lastName, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            return new ResponseEntity<>(new ResponseMessage("Email already used!").getMessage(), HttpStatus.BAD_REQUEST);
        }

        CustomUser newAcc = new CustomUser(firstName, lastName, email, password);
        userRepository.saveAndFlush(newAcc);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully").getMessage(), HttpStatus.CREATED);
    }


    public ResponseEntity<?> authenticateUser(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(new ResponseMessage("User signed-in successfully!").getMessage(), HttpStatus.OK);

    }

}
