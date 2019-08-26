package com.finalassignment.social.controllers;

import com.finalassignment.social.models.User;
import com.finalassignment.social.services.UserService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public void removeUserById(@PathVariable Integer id){
        userService.removeUserById(id);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Integer id){
        return userService.updateUser(user, id);
    }

}
