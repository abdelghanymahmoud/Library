package com.library.controllers;

import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public List<User> getUsers(){
        return userService.getUsers();
    }

//    @PostMapping(value = "/addBook")
//    public void addBook(@RequestBody String title){
//        userService.addBook(title);
//    }
}
