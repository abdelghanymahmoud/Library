package com.library.controllers;

import com.library.dto.JwtResponse;
import com.library.dto.UserRequest;
import com.library.logger.LibraryLogger;
import com.library.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signup")
public class RegistrationController {
    final private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = { "/", "" })
    public ResponseEntity<?> signup(@RequestBody UserRequest request) throws Exception {
        LibraryLogger.log.info("Enter signup method of RegistrationController class");
        JwtResponse token = registrationService.saveUser(request);
        LibraryLogger.log.info("Before return statement in signup method of RegistrationController class");
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
