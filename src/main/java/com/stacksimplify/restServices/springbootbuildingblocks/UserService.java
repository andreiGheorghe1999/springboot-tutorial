package com.stacksimplify.restServices.springbootbuildingblocks;

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
    public UserEntity createUser( UserEntity user){
        return userRepository.save(user);
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
    public UserEntity updateUserById(Long id, UserEntity newUserContent){
        newUserContent.setId(id);
        return userRepository.save(newUserContent);
    }

    public void deleteUserById(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
