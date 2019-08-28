package com.finalassignment.social.controllers;

import com.finalassignment.social.exceptions.UserAlreadyExistsException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.UserProfile;
import com.finalassignment.social.services.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class UserProfileController {
    UserProfileService userProfileService;

    @Autowired
    UserProfileController(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    /**
     * To get all the User Profiles
     * @return
     */
    @GetMapping("/users/{id}/profile")
    public ResponseEntity<List<UserProfile>> getAllUserProfiles(){
        log.debug("Getting All UserProfiles, In UserProfileController");
        return ResponseEntity.status(HttpStatus.OK).body(userProfileService.getAllUserProfiles());
    }

    /**
     * To create a new User Profile
     * @param userProfile
     * @param id
     * @return
     * @throws UserAlreadyExistsException
     */

    @PostMapping("/users/{id}/profile")
    public ResponseEntity<UserProfile> createUserProfile(@Valid @RequestBody UserProfile userProfile, @Valid @PathVariable Integer id) throws UserAlreadyExistsException {
        log.debug("Creating UserProfile, In UserProfileController");
        userProfile.setUserId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileService.createUserProfile(userProfile));
    }

    /**
     * To get a User Profile By ID
     * @param id
     * @return
     */
    @GetMapping("/users/{id}/profile/{user_id}")
    public ResponseEntity<UserProfile> getUserProfileById(@Valid @PathVariable(value = "user_id") Integer id){
        log.debug("Finding a UserProfile, In UserProfileController");
        return ResponseEntity.status(HttpStatus.OK).body(userProfileService.getUserProfileById(id));
    }

    /**
     * To remove a user Profile By ID
     * @param id
     * @return
     */

    @DeleteMapping("/users/{id}/profile")
    public ResponseEntity<Void> removeUserProfileById(@Valid @PathVariable Integer id){
        log.debug("Removing a UserProfile, In UserProfileController");
        userProfileService.removeUserProfileById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * To Update a User Profile
     * @param userProfile
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @PutMapping("/users/{id}/profile")
    public ResponseEntity<UserProfile> updateUserProfile(@Valid @RequestBody UserProfile userProfile, @Valid @PathVariable Integer id) throws UserNotFoundException {
        log.debug("Updating a UserProfile, In UserProfileController");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userProfileService.updateUserProfile(userProfile, id));
    }
}
