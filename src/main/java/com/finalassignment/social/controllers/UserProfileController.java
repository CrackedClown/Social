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

    @GetMapping("/users/{id}")
    public List<UserProfile> getAllUserProfiles(){
        return userProfileService.getAllUserProfiles();
    }

    @PostMapping("/user/{id}/user_profile")
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile, @PathVariable Integer id){
        userProfile.setUserId(id);
        return userProfileService.createUserProfile(userProfile);
    }

    @GetMapping("/user/{id}/user_profile")
    public UserProfile findUserProfileById(@PathVariable(value = "user_id") Integer id){
        return userProfileService.findUserProfileById(id);
    }

    @DeleteMapping("/user/{id}/user_profile")
    public void removeUserProfile(@RequestBody UserProfile userProfile){
        userProfileService.removeUserProfile(userProfile);
    }

    @PutMapping("/users/{id}/user_profile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile, @PathVariable Integer id){
        return userProfileService.updateUserProfile(userProfile, id);
    }


}
