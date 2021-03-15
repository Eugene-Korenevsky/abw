package com.example.abw.servicies.logic;

import com.example.abw.entities.user.User;
import com.example.abw.repositories.user.UserRepository;
import com.example.abw.servicies.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository, User.class);
        this.userRepository = userRepository;
    }
}
