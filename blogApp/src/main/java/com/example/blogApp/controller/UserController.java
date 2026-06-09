package com.example.blogApp.controller;
import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public void createUser(UserDTO userDTO){

        userService.addUser(userDTO);
    }


}
