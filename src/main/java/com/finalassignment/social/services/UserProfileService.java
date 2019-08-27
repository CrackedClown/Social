package com.finalassignment.social.services;

import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.UserProfile;
import com.finalassignment.social.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Autowired
    UserProfileService(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }
    public UserProfile findUserProfileById(Integer id){
        return userProfileRepository.findById(id).orElse(null);
    }

    public UserProfile createUserProfile(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public void removeUserProfileById(Integer id){
        userProfileRepository.deleteById(id);
    }

    public UserProfile updateUserProfile(UserProfile userProfile, Integer id) throws UserNotFoundException {
            UserProfile tempUserProfile = userProfileRepository.findById(id).orElseThrow(()-> new UserNotFoundException("UserNotFound"));
            userProfile.setUserId(id);
            return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }


}
