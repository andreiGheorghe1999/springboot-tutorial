package com.stacksimplify.restServices.springbootbuildingblocks;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Class<? extends UserMapper> INSTANCE = Mappers.getMapperClass(UserMapper.class);


    @Mappings({@Mapping(target = "emailAddress", source = "email"), @Mapping(target = "rolename", source = "role")}) // Multiple mappings
    UserMapStructDTO userToUserDTO(UserEntity userEntity);

    List<UserMapStructDTO> usersToUserDto(List<UserEntity> users);
}
