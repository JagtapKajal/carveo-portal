package carveo_portal.carveoManagement.controller;

import carveo_portal.carveoManagement.entity.User;
import carveo_portal.carveoManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.createUser(user);


    }
}
