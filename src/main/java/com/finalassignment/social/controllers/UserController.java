package com.finalassignment.social.controllers;

import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.User;
import com.finalassignment.social.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> removeUserById(@PathVariable Integer id){
        userService.removeUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(user, id));
    }
}
