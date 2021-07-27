package com.library.controllers;

import com.library.dto.UserRequest;
import com.library.logger.LibraryLogger;
import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<?> getUsers(){
        LibraryLogger.log.info("Enter method getUsers in UserController class");
        LibraryLogger.log.info("Before the end of method getUsers in UserController class");
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        LibraryLogger.log.info("Enter method getUserById in UserController class");
        LibraryLogger.log.info("Before the end of method getUserById in UserController class");
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userRequest){
        LibraryLogger.log.info("Enter method updateUser in UserController class");
        LibraryLogger.log.info("Before the end of method updateUser in UserController class");
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        LibraryLogger.log.info("Enter method deleteUser in UserController class");
        userService.deleteUserById(id);
        LibraryLogger.log.info("Before the end of method deleteUser in UserController class");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
