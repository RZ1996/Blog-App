package com.example.blogApp.service;
import com.example.blogApp.dto.AuthResponse;
import com.example.blogApp.dto.LoginRequest;
import com.example.blogApp.dto.RegisterRequest;
import com.example.blogApp.dto.UserDTO;
import com.example.blogApp.entity.RoleStatus;
import com.example.blogApp.entity.User;
import com.example.blogApp.mapper.UserMapper;
import com.example.blogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;


    public UserDTO addUser(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ← BCrypt
        user.setStatus(RoleStatus.USER);
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public AuthResponse loginUser(LoginRequest request) {
        User user = findByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token, "Bearer", user.getId(), user.getName(), user.getEmail());
    }
}
