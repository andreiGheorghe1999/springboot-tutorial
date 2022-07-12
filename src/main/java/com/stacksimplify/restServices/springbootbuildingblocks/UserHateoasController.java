package com.stacksimplify.restServices.springbootbuildingblocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public CollectionModel<UserEntity> getUsers() throws UserNotFound {

        List<UserEntity> userEntities = new ArrayList<>();
        for(UserEntity userEntity : userService.getAllUsers()){
            // Retrieve the user id in order to create self-link
            Long userId = userEntity.getUserId();
            // We need to provide the object we are providing a reference to and the type of relationship that is used
            // In this case a self-link Relationship
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
            userEntity.add(selfLink);
            // In this case we also need to provide the relationship with the orders
            CollectionModel<OrderEntity> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
            Link ordersRel = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
            userEntity.add(ordersRel);
            // Add to list of user entities
            userEntities.add(userEntity);
        }
        // Self link for all users
        Link selfLinkAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        CollectionModel<UserEntity> model = CollectionModel.of(userEntities,selfLinkAllUsers);
        return model;
    }

    @GetMapping("/{id}")
    public EntityModel<UserEntity> getUserById(@PathVariable("id") @Min(1) Long id) {
        // The path variable annotation is used to define paths starting from the same endpoint in order to differentiate between certain criterias
        // EX : /users/{id} -- get users by id while /users/{name} -- get users by name

        try {
            Optional<UserEntity> userEntityOptional = userService.getUserById(id);
            if(userEntityOptional.isPresent()){
                UserEntity userEntity = userEntityOptional.get();
                // Retrieve the user id in order to create self-link
                Long userId = userEntity.getUserId();
                // We need to provide the object we are providing a reference to and the type of relationship that is used
                // In this case a self-link Relationship
                Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
                userEntity.add(selfLink);
                // Define the new entity as resource
                return EntityModel.of(userEntity);
            }
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return null;
    }
}
