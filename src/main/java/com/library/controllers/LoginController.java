package com.library.controllers;

import com.library.dto.JwtResponse;
import com.library.dto.UserRequest;
import com.library.logger.LibraryLogger;
import com.library.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/signin")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = { "/", "" })
    public ResponseEntity<?> login(@RequestBody UserRequest request) throws Exception {
        LibraryLogger.log.info("Enter login method of LoginController class");
        JwtResponse response = loginService.doLogin(request);
        if (response == null) {
            LibraryLogger.log.info("Before return statement in login method of LoginController class as FORBIDDEN access");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        LibraryLogger.log.info("Before return statement in login method of LoginController class as ok access");
        return ResponseEntity.ok(response);
    }
}
