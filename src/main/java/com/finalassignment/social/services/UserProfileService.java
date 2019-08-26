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

    public void removeUserProfileById(Integer id){
        userProfileRepository.deleteById(id);
    }

    public UserProfile updateUserProfile(UserProfile userProfile, Integer id){
        if(userProfileRepository.existsById(id)){
            UserProfile tempUserProfile = userProfileRepository.getOne(id);
            tempUserProfile.setUserId(id);
            tempUserProfile.setFirstName(userProfile.getFirstName());
            tempUserProfile.setLastName(userProfile.getFirstName());
            userProfileRepository.saveAndFlush(tempUserProfile);
            return tempUserProfile;
        }
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }


}
