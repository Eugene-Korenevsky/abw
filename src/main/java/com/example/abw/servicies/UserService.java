package com.example.abw.servicies;

import com.example.abw.entities.user.User;
import com.example.abw.exception.user.EmailIsAlreadyExistException;
import com.example.abw.exception.user.UserOrPasswordException;
import com.example.abw.model.pageable.PageableParams;

import java.util.List;

public interface UserService extends GenericService<User> {
    public List<User> findAll(PageableParams pageableParams);

    public User saveUser(User user) throws EmailIsAlreadyExistException;

    public User findByEmail(String email);

    public User findByEmailAndPassword(String email, String password) throws UserOrPasswordException;
}
