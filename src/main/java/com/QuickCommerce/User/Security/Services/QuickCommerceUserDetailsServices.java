package com.QuickCommerce.User.Security.Services;

import com.QuickCommerce.User.Models.User;
import com.QuickCommerce.User.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuickCommerceUserDetailsServices implements UserDetailsService {

    UserRepo userRepo;

    public QuickCommerceUserDetailsServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findUsersByEmail(email);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User with the email "+ email +" not found");
        }
        User user = userOptional.get();
        return new QuickCommerceUserDetails(user);
    }
}
