package com.finalassignment.social.controllers;

import com.finalassignment.social.exceptions.UserAlreadyExistsException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.User;
import com.finalassignment.social.services.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * To get the list of all users
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        log.debug("Getting All Users, In UserController");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    /**
     * To create a new User
     * @param user
     * @return
     * @throws UserAlreadyExistsException
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException {
        log.debug("Creating a User, In UserController");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    /**
     * To find a user by ID
     * @param id
     * @return
     * @throws UserNotFoundException
     */

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) throws UserNotFoundException {
        log.debug("Finding A User, In UserController");
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    /**
     * To remove a user by ID
     * @param id
     * @return
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> removeUserById(@PathVariable Integer id){
        log.debug("Removing a User, In UserController");
        userService.removeUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * To update a user by ID
     * @param user
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Integer id) throws UserNotFoundException {
        log.debug("Updating User, In UserController");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(user, id));
    }
}
