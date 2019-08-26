package com.finalassignment.social.services;

import com.finalassignment.social.models.User;
import com.finalassignment.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void removeUserById(Integer id){
        userRepository.deleteById(id);
    }

    public User updateUser(User user, Integer id){
        if(userRepository.existsById(id)){
            User tempUser = userRepository.findById(id).orElse(null);
            tempUser.setUsername(user.getUsername());
            tempUser.setPassword(user.getPassword());
            tempUser.setUserProfile(user.getUserProfile());
            return userRepository.save(tempUser);
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
