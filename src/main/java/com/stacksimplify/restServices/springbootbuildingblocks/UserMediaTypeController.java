package com.stacksimplify.restServices.springbootbuildingblocks;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/mediaTypeVersioning/users")
public class UserMediaTypeController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    //Uri-based versioning
    @GetMapping(value = "/{id}",  produces="application/vnd.stacksimplify.app-v1+json")
    public UserDTOV1 getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            Optional<UserEntity> userEntityOptional = userService.getUserById(id);
            UserEntity userEntity = userEntityOptional.get();
            UserDTOV1 userModelMapperDTO = modelMapper.map(userEntity, UserDTOV1.class);

            return userModelMapperDTO;
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    // Test request using accept header
    @GetMapping(value = "/{id}", produces="application/vnd.stacksimplify.app-v2+json")
    public UserDTOV2 getUserById2(@PathVariable("id") @Min(1) Long id) {
        try {
            Optional<UserEntity> userEntityOptional = userService.getUserById(id);
            UserEntity userEntity = userEntityOptional.get();
            UserDTOV2 userModelMapperDTO = modelMapper.map(userEntity, UserDTOV2.class);

            return userModelMapperDTO;
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
