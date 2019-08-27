package com.finalassignment.social.services;

import com.finalassignment.social.exceptions.UserAlreadyExistsException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.User;
import com.finalassignment.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    //Manually checking username exists or not because unique keyboard is not working for some reason!
    public User createUser(User user) throws UserAlreadyExistsException {
        List<User> userList = userRepository.findAll();
        for(User tempUser : userList){
            if(user.getUsername().equals(tempUser.getUsername())){
                throw new UserAlreadyExistsException("Username Already Exists");
            }
        }
        return userRepository.save(user);
    }

    public void removeUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user, Integer id) throws UserNotFoundException {
        User tempUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("UserNotFound"));
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setUserProfile(user.getUserProfile());
        return userRepository.save(tempUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
