package com.finalassignment.social.controllers;

import com.finalassignment.social.models.UserProfile;
import com.finalassignment.social.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserProfile> getAllUserProfiles(){
        return userProfileService.getAllUserProfiles();
    }

    @PostMapping("/users/{id}/profile")
    public UserProfile createUserProfile(@Valid @RequestBody UserProfile userProfile, @Valid @PathVariable Integer id){
        userProfile.setUserId(id);
        return userProfileService.createUserProfile(userProfile);
    }

    @GetMapping("/users/{id}/profile/{user_id}")
    public UserProfile findUserProfileById(@Valid @PathVariable(value = "user_id") Integer id){
        return userProfileService.findUserProfileById(id);
    }

    @DeleteMapping("/users/{id}/profile")
    public void removeUserProfileById(@Valid @PathVariable Integer id){
        userProfileService.removeUserProfileById(id);
    }

    @PutMapping("/users/{id}/profile")
    public UserProfile updateUserProfile(@Valid @RequestBody UserProfile userProfile, @Valid @PathVariable Integer id){
        return userProfileService.updateUserProfile(userProfile, id);
    }
}
