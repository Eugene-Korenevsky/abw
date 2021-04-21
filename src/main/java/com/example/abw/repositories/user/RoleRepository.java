package com.example.abw.repositories.user;

import com.example.abw.entities.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    public Role findByRole(String role);
}
