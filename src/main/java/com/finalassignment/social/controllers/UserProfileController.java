package com.finalassignment.social.controllers;

import com.finalassignment.social.exceptions.UserAlreadyExistsException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.UserProfile;
import com.finalassignment.social.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserProfileController {
    UserProfileService userProfileService;

    @Autowired
    UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @GetMapping("/users/{id}/profile")
    public ResponseEntity<List<UserProfile>> getAllUserProfiles(){
        return ResponseEntity.status(HttpStatus.OK).body(userProfileService.getAllUserProfiles());
    }

    @PostMapping("/users/{id}/profile")
    public ResponseEntity<UserProfile> createUserProfile(@Valid @RequestBody UserProfile userProfile, @Valid @PathVariable Integer id) throws UserAlreadyExistsException {
        userProfile.setUserId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileService.createUserProfile(userProfile));
    }

    @GetMapping("/users/{id}/profile/{user_id}")
    public ResponseEntity<UserProfile> findUserProfileById(@Valid @PathVariable(value = "user_id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userProfileService.findUserProfileById(id));
    }

    @DeleteMapping("/users/{id}/profile")
    public ResponseEntity<Void> removeUserProfileById(@Valid @PathVariable Integer id){
        userProfileService.removeUserProfileById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/{id}/profile")
    public ResponseEntity<UserProfile> updateUserProfile(@Valid @RequestBody UserProfile userProfile, @Valid @PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userProfileService.updateUserProfile(userProfile, id));
    }
}
