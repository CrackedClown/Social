package com.finalassignment.social.services;

import com.finalassignment.social.exceptions.UserAlreadyExistsException;
import com.finalassignment.social.exceptions.UserNotFoundException;
import com.finalassignment.social.models.User;
import com.finalassignment.social.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserById(Integer id) throws UserNotFoundException {
        log.debug("Finding A User, In UserService");
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        log.debug("Creating a User, In UserService");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void removeUserById(Integer id) {
        log.debug("Removing a User, In UserService");
        userRepository.deleteById(id);
    }

    public User updateUser(User user, Integer id) throws UserNotFoundException {
        log.debug("Updating User, In UserService");
        User tempUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("UserNotFound"));
        tempUser.setUsername(user.getUsername());
        tempUser.setPassword(user.getPassword());
        tempUser.setUserProfile(user.getUserProfile());
        return userRepository.save(tempUser);
    }

    public List<User> getAllUsers() {
        log.debug("Getting All Users, In UserService");
        return userRepository.findAll();
    }
}
