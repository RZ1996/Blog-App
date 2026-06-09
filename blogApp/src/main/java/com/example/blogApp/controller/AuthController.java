package com.example.blogApp.controller;
import com.example.blogApp.dto.LoginRequest;
import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        UserDTO savedUser = userService.addUser(userDTO);
        return ResponseEntity.status(201).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginRequest request){
        UserDTO userDTO = userService.loginUser(request);
        return ResponseEntity.ok(userDTO);
    }


}
