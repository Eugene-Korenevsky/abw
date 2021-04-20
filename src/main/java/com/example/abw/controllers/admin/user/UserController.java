package com.example.abw.controllers.admin.user;

import com.example.abw.entities.user.User;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("adminUserController")
@RequestMapping("admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(PageableParams pageableParams) {
        List<User> users = userService.findAll(pageableParams);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
