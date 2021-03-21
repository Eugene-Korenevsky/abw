package com.example.abw.controllers.admin.user;

import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.user.User;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.pagination.user.simple.UserPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("adminUserController")
@RequestMapping("admin/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPaginationService userPaginationService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        if (page == null && size == null) {
            List<User> users = userPaginationService.defaultUserPagination(0);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else if (page == null) {
            List<User> users = userPaginationService.defaultUserPaginationWithSize(0, size);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else if (size == null) {
            List<User> users = userPaginationService.defaultUserPagination(page);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            List<User> users = userPaginationService.defaultUserPaginationWithSize(page, size);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
}
