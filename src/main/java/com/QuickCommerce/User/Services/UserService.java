package com.QuickCommerce.User.Services;

import com.QuickCommerce.User.DTOs.UserDTO;
import com.QuickCommerce.User.Exceptions.InvalidArgException;
import com.QuickCommerce.User.Models.Token;
import com.QuickCommerce.User.Models.User;
import com.QuickCommerce.User.Repos.TokenRepo;
import com.QuickCommerce.User.Repos.UserRepo;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
* Service layer should not take or return DTO objects. It should take and return model objects.
* as DTO are only restricted to Controller layer.
* */
@Service
public class UserService {

    UserRepo userRepo;
    TokenRepo tokenRepo;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    TokenService tokenService;

    public UserService(UserRepo userRepo,
                       TokenRepo tokenRepo,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenService tokenService){
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
    }

    public User registerUser(String name, String email, String password)throws InvalidArgException{
        if(name == null || email == null || password == null){
            throw new InvalidArgException("Email or password cannot be null");
        }
        Optional<User> userOptional = userRepo.findUsersByEmail(email);
        if(!userOptional.isEmpty()){
            throw new InvalidArgException("User already exists, Please login");
        }
        //TODO - add more checks for name, password and email for the length, special characters etc.
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(user);
    }

    public Token loginUser(String email, String password)throws InvalidArgException{
        if(email == null || password == null){
            throw new InvalidArgException("Email or password cannot be null");
        }
        Optional<User> user = userRepo.findUsersByEmail(email);
        if(user.isEmpty()){
            throw new InvalidArgException("User not found, Please register");
        }
        if(!bCryptPasswordEncoder.matches(password, user.get().getHashedPassword())){
            throw new InvalidArgException("Invalid password");
        }
        Token token = tokenService.createToken(user.get());
        tokenRepo.save(token);
        return token;
    }

    public User validateUser(String token)throws InvalidArgException{
        Optional<Token> tokenOptional = tokenRepo.findTokenByValue(token);
        if(tokenOptional.isEmpty()){
           throw new InvalidArgException("Invalid token");
        }
        Token dbToken = tokenOptional.get();
        if(dbToken.getExpiryDate().before(new java.util.Date())){
            throw new InvalidArgException("Token expired please login again");
        }
        if(dbToken.getIsDeleted()){
            throw new InvalidArgException("Token is Deleted/ User has already logged out");
        }
        return dbToken.getUser();
    }

    public String logoutUser(String token)throws InvalidArgException{
        Optional<Token> tokenOptional = tokenRepo.findTokenByValue(token);
        if(tokenOptional.isEmpty()){
            throw new InvalidArgException("User not found, Please register");
        }
        Token dbToken = tokenOptional.get();
        dbToken.setIsDeleted(true);
        tokenRepo.save(dbToken);
        return "User logged out successfully";
    }

    public UserDTO getUserById(){
        return null;
    }

    public String deleteUserById(){
        return null;
    }
}
