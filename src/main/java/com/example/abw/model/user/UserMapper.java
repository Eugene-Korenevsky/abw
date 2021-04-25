package com.example.abw.model.user;

import com.example.abw.entities.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {


    public UserDTO userToUserDTO(User user);

    public User userDTOToUser(UserDTO userDTO);
}
