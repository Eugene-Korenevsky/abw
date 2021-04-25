package com.example.abw.model.user;

import com.example.abw.entities.user.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    public RoleDTO roleToRoleDTO(Role role);

    public Role roleDTOToRole(RoleDTO roleDTO);
}
