package com.library.services;

import com.library.logger.LibraryLogger;
import com.library.models.User;
import com.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        LibraryLogger.log.info("Enter method getUsers in UserService class");
        LibraryLogger.log.info("Before the end of method getUsers in UserController class");
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        LibraryLogger.log.info("Enter method getUsers in UserService class");
        User user = userRepository.findById(id).get();
        LibraryLogger.log.info("Before the end of method getUsers in UserController class");
        return user;
    }

    public User updateUser(Long id, User userRequest){
        LibraryLogger.log.info("Enter method getUsers in UserService class");
        User user = userRepository.findById(id).get();
        if(user.getId() != userRequest.getId()){
            return null;
        }
        user.setEmail(userRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        user.setName(userRequest.getName());
        userRepository.save(user);
        LibraryLogger.log.info("Before the end of method getUsers in UserController class");
        return user;
    }

    public void deleteUserById(Long id){
        LibraryLogger.log.info("Enter method deleteUserById in UserService class");
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        LibraryLogger.log.info("Before the end of method deleteUserById in UserController class");
    }

}
