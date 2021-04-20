package com.example.abw.servicies;

import com.example.abw.entities.user.User;
import com.example.abw.model.pageable.PageableParams;

import java.util.List;

public interface UserService extends GenericService<User> {
    public List<User> findAll(PageableParams pageableParams);
}
