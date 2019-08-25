package com.finalassignment.social.controllers;

import com.finalassignment.social.models.User;
import com.finalassignment.social.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    @DeleteMapping("/user")
    public void removeUserById(@RequestBody User user){
        userService.removeUser(user);
    }
}
