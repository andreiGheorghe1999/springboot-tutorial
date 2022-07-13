package com.stacksimplify.restServices.springbootbuildingblocks;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/userJsonView")
public class UserJsonViewController {
    @Autowired
    UserService userService;

    @JsonView(Views.External.class)
    @GetMapping("/external/{id}")
    public UserEntity getUserByIdExternal(@PathVariable("id") @Min(1) Long id) {
        // The path variable annotation is used to define paths starting from the same endpoint in order to differentiate between certain criterias
        // EX : /usersJacksonValue/{id} -- get users by id while /usersJacksonValue/{name} -- get users by name
        try {
            Optional<UserEntity> userEntityOptional = userService.getUserById(id);
            UserEntity userEntity = userEntityOptional.get();
            return userEntity;
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/internal/{id}")
    public UserEntity getUserByIdInternal(@PathVariable("id") @Min(1) Long id) {
        // The path variable annotation is used to define paths starting from the same endpoint in order to differentiate between certain criterias
        // EX : /usersJacksonValue/{id} -- get users by id while /usersJacksonValue/{name} -- get users by name
        try {
            Optional<UserEntity> userEntityOptional = userService.getUserById(id);
            UserEntity userEntity = userEntityOptional.get();
            return userEntity;
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
