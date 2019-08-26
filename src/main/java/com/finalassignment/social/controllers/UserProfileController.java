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

    @GetMapping("/users/{id}/user_profile")
    public List<UserProfile> getAllUserProfiles(){
        return userProfileService.getAllUserProfiles();
    }

    @PostMapping("/users/{id}/user_profile")
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile, @PathVariable Integer id){
        userProfile.setUserId(id);
        return userProfileService.createUserProfile(userProfile);
    }

    @GetMapping("/users/{id}/user_profile/{user_id}")
    public UserProfile findUserProfileById(@PathVariable(value = "user_id") Integer id){
        return userProfileService.findUserProfileById(id);
    }

    @DeleteMapping("/users/{id}/user_profile")
    public void removeUserProfileById(@PathVariable Integer id){
        userProfileService.removeUserProfileById(id);
    }

    @PutMapping("/users/{id}/user_profile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile, @PathVariable Integer id){
        return userProfileService.updateUserProfile(userProfile, id);
    }
}
