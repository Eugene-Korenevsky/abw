package com.example.abw.controllers.admin.user;

import com.example.abw.entities.user.User;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.UserService;
import com.example.abw.utils.pageable_params.PageableParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
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
    private PageableParamsUtil pageableParamsUtilImpl;

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "size", required = false) Integer size) {
        PageableParams pageableParams = pageableParamsUtilImpl.getPageableParams(page, size, null, null);
        try {
            List<User> users = userService.findAll(pageableParams);
            for (User user : users) {
                System.out.println(user.getName());
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (PropertyReferenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
