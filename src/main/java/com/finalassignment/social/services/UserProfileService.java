package com.finalassignment.social.services;

import com.finalassignment.social.models.User;
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

    public void removeUserProfile(UserProfile userProfile){
        userProfileRepository.delete(userProfile);
    }

    public UserProfile updateUserProfile(UserProfile userProfile){
        if(userProfileRepository.existsById(userProfile.getUserId())){
            return userProfileRepository.save(userProfile);
        }
        return null;
    }

    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }


}