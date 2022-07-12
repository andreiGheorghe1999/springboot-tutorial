package com.stacksimplify.restServices.springbootbuildingblocks;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Autowire the user repository
    @Autowired
    private UserRepository userRepository;

    // Method for retrieving all users
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    // Create new user
    public void createUser( UserEntity user) throws UserExists{
        UserEntity existingUser = findByUsername(user.getUsername());
        if(existingUser != null){
            throw new UserExists("User with username : " + user.getUsername() + " already exists.");
        }
        userRepository.save(user);
    }

    // Get user by id
    public Optional<UserEntity> getUserById(Long id) throws UserNotFound{
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()){
            throw new UserNotFound("User with id " +id + " not found.");
        }
        return userEntity;
    }

    // Update user by id
    public UserEntity updateUserById(Long id, UserEntity newUserContent) throws UserNotFound{
        Optional<UserEntity> foundEntity = userRepository.findById(id);
        if(foundEntity.isPresent()){
            newUserContent.setUserId(id);
        }
        else{
            throw new UserNotFound("User with id : " + id + " not found.");
        }
        return userRepository.save(newUserContent);
    }

    public void deleteUserById(Long id) throws  UserNotFound{
        Optional<UserEntity> foundEntity = userRepository.findById(id);
        if(foundEntity.isPresent()){
            userRepository.deleteById(id);
        }
        else{
            throw new UserNotFound("User with id : " + id + " not found.");
        }
    }

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
