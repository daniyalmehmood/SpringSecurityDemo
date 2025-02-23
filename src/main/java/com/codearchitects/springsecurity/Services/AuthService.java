package com.codearchitects.springsecurity.Services;


import com.codearchitects.springsecurity.Configs.UserRepository;
import com.codearchitects.springsecurity.DTOs.SignInRequestDTO;
import com.codearchitects.springsecurity.DTOs.SignUpRequestDTO;
import com.codearchitects.springsecurity.Models.User;
import com.codearchitects.springsecurity.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    // Sign-in method for JWT generation
    public String signIn(SignInRequestDTO dto) throws Exception {
        // Find user by username
        User user = userRepository.findByUserName(dto.getUserName());
        // Check password match
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("Invalid username or password");
        }

        // Generate JWT token
        return jwtUtil.generateToken(user.getUserName(), user.getEmail(), user.getRole().name());
    }
    public void signUp(SignUpRequestDTO dto) throws Exception {
        if (userRepository.findByUserName(dto.getUserName()) == null) {
            throw new Exception("Username already exists");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new Exception("Email already exists");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUserName());
        user.setRole(dto.getRole());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);

      /*  userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .role(dto.getRole())
                        .userName(dto.getUserName()).build()
        );*/
    }
}
