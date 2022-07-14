package com.stacksimplify.restServices.springbootbuildingblocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/mapStruct/users")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    private List<UserMapStructDTO> getAllUsersDTO() {
        return userMapper.usersToUserDto(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMapStructDTO getUserById(@PathVariable("id") @Min(1) Long id) {

        return userMapper.userToUserDTO(userRepository.findById(id).get());
    }

}
