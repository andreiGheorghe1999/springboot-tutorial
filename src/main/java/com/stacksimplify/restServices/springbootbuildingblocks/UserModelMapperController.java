package com.stacksimplify.restServices.springbootbuildingblocks;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserModelMapperDTO getUserById(@PathVariable("id") @Min(1) Long id){
        try {
            Optional<UserEntity> userEntityOptional =  userService.getUserById(id);
            UserEntity userEntity = userEntityOptional.get();
            UserModelMapperDTO userModelMapperDTO = modelMapper.map(userEntity, UserModelMapperDTO.class);
            return userModelMapperDTO;
        } catch (UserNotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
