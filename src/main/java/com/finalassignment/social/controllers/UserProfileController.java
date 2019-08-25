package com.finalassignment.social.controllers;

import com.finalassignment.social.models.UserProfile;
import com.finalassignment.social.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserProfileController {
    UserProfileService userProfileService;

    @Autowired
    UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    @PostMapping("/users")
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile){
        return userProfileService.createUserProfile(userProfile);
    }

    @GetMapping("/users/{id}")
    public UserProfile findUserProfileById(@PathVariable Integer id){
        return userProfileService.findUserProfileById(id);
    }

    @DeleteMapping("/users")
    public void removeUserProfile(@RequestBody UserProfile userProfile){
        userProfileService.removeUserProfile(userProfile);
    }

    @PutMapping("/users/{id}")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile, @PathVariable Integer id){
        return userProfileService.updateUserProfile(userProfile);
    }

    @GetMapping("/users")
    public List<UserProfile> getAllUserProfiles(){
        return userProfileService.getAllUserProfiles();
    }
}
