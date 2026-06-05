package com.example.blogApp.service;

import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.entity.User;
import com.example.blogApp.mapper.UserMapper;
import com.example.blogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

  public UserDTO addUser(UserDTO userDTO){
      User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
      return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
  }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public UserDTO loginUser(String email, String password) {
        User user = findByEmail(email);
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }
        return UserMapper.INSTANCE.mapUserToUserDTO(user);
    }
}
