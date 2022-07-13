package com.stacksimplify.restServices.springbootbuildingblocks;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@Validated
@RequestMapping(value = "/usersJacksonValue")
public class UserMappingJacksonValue {
    @Autowired
    UserService userService;
    /// Fields with Hashset
    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
        // The path variable annotation is used to define paths starting from the same endpoint in order to differentiate between certain criterias
        // EX : /usersJacksonValue/{id} -- get users by id while /usersJacksonValue/{name} -- get users by name
        try {
            Optional<UserEntity> userEntityOptional = userService.getUserById(id);
            UserEntity userEntity = userEntityOptional.get();
            // Create the Mapping Jackson Value
            MappingJacksonValue mapper = new MappingJacksonValue(userEntity);
            Set<String> fieldsToDisplay = new HashSet<String>();
            fieldsToDisplay.add("userId");
            fieldsToDisplay.add("username");
            fieldsToDisplay.add("ssn");
            fieldsToDisplay.add("orders");
            // This filter has to be applied on the entity itself using @JsonFilter
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    fieldsToDisplay
            )); // There can be added multiple chained filters.

            mapper.setFilters(filterProvider);

            return mapper;
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    // Fields with @RequestParam
    @GetMapping("/params/{id}")
    public MappingJacksonValue getUserByIdUsingParams(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> parameterList) {
        // The path variable annotation is used to define paths starting from the same endpoint in order to differentiate between certain criterias
        // EX : /usersJacksonValue/{id} -- get users by id while /usersJacksonValue/{name} -- get users by name
        try {
            Optional<UserEntity> userEntityOptional = userService.getUserById(id);
            UserEntity userEntity = userEntityOptional.get();
            // Create the Mapping Jackson Value
            MappingJacksonValue mapper = new MappingJacksonValue(userEntity);
            // This filter has to be applied on the entity itself using @JsonFilter
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(
                    parameterList
            )); // There can be added multiple chained filters.

            mapper.setFilters(filterProvider);

            return mapper;
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
