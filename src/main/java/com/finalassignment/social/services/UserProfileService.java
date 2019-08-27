package com.finalassignment.social.services;

import com.finalassignment.social.exceptions.UserAlreadyExistsException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.User;
import com.finalassignment.social.models.UserProfile;
import com.finalassignment.social.repositories.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Autowired
    UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getUserProfileById(Integer id) {
        log.debug("Finding a UserProfile, In UserProfileService");
        return userProfileRepository.findById(id).orElse(null);
    }

    public UserProfile createUserProfile(UserProfile userProfile) throws UserAlreadyExistsException {
        log.debug("Creating UserProfile, In UserProfileService");
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        for (UserProfile tempUserProfile : userProfileList) {
            if ((tempUserProfile.getEmail()).equals(userProfile.getEmail())) {
                throw new UserAlreadyExistsException("User Profile Already Exists");
            }
        }
        return userProfileRepository.save(userProfile);
    }

    public void removeUserProfileById(Integer id) {
        log.debug("Removing a UserProfile, In UserProfileService");
        userProfileRepository.deleteById(id);
    }

    public UserProfile updateUserProfile(UserProfile userProfile, Integer id) throws UserNotFoundException {
        log.debug("Updating a UserProfile, In UserProfileService");
        UserProfile tempUserProfile = userProfileRepository.findById(id).orElseThrow(() -> new UserNotFoundException("UserNotFound"));
        userProfile.setUserId(id);
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles() {
        log.debug("Getting All UserProfiles, In UserProfileService");
        return userProfileRepository.findAll();
    }


}
