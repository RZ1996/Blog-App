package com.example.blogApp.mapper;
import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdAt", source = "createdAt")
    UserDTO mapUserToUserDTO(User user);

    // UserDTO → User
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "posts", ignore = true)
    User mapUserDTOToUser(UserDTO userDTO);
}