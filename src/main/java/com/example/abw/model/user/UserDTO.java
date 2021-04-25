package com.example.abw.model.user;

import com.example.abw.entities.user.Role;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Role role;
}
