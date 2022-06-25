package com.stacksimplify.restServices.springbootbuildingblocks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<UserEntity> getUsers()
    {
        return userService.getAllUsers();
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody UserEntity user, UriComponentsBuilder uriComponentsBuilder){
        try {
            userService.createUser(user);
            // Return the location of the user that is created with that unique username in order to be checked
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
        } catch (UserExists e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id") Long id){
        // The path variable annotation is used to define paths starting from the same endpoint in order to differentiate between certain criterias
        // EX : /users/{id} -- get users by id while /users/{name} -- get users by name
        try {
            return userService.getUserById(id);
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public UserEntity updateUserById(@PathVariable("id") Long id, @RequestBody UserEntity userEntity){
        try {
            return userService.updateUserById(id,userEntity);
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        try {
            userService.deleteUserById(id);
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("/users/byusername/{username}")
    public UserEntity getUserByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }
}
