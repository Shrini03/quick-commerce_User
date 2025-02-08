package com.QuickCommerce.User.Controllers;

import com.QuickCommerce.User.DTOs.*;
import com.QuickCommerce.User.Exceptions.InvalidArgException;
import com.QuickCommerce.User.Models.Token;
import com.QuickCommerce.User.Models.User;
import com.QuickCommerce.User.Repos.UserRepo;
import com.QuickCommerce.User.Services.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserRepo userRepo;
    private UserService userService;

    public UserController(UserService userService, UserRepo userRepo){
        this.userService = userService;
        this.userRepo = userRepo;
    }
    @PostMapping("/register")
    public RegisterResponseDTO registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) throws InvalidArgException {
        User user = userService.registerUser(registerRequestDTO.getName(),
                                             registerRequestDTO.getEmail(),
                                             registerRequestDTO.getPassword());
        return new RegisterResponseDTO(user.getEmail(), "User registered successfully, Please login");
    }
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO loginRequestDTO) throws InvalidArgException {
        Token  token = userService.loginUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        return new LoginResponseDTO(token.getUser().getEmail(),
                "User logged in successfully", token.getValue());
    }

    @PostMapping("/logout")
    public String logoutUser(@RequestHeader("Authorization") String token) throws InvalidArgException {
        return userService.logoutUser(token);
    }
    @PostMapping("/validate")
    public UserDTO validateUser(@RequestHeader("Authorization") String token) throws InvalidArgException {
        User user =  userService.validateUser(token);
        return new UserDTO(user.getName(), user.getEmail());
    }

    public UserDTO getUserById(Long id){
        return null;
    }

    public String deleteUser(String token){
        return null;
    }
}
