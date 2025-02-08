package com.QuickCommerce.User.Services;

import com.QuickCommerce.User.Models.Token;
import com.QuickCommerce.User.Models.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    public Token createToken(User user){
        Token token = new Token();
        token.setValue(UUID.randomUUID().toString());
        token.setExpiryDate(new Date(System.currentTimeMillis() + 1000*60*60*24));// set to 1 day expiry
        token.setIsDeleted(false);
        token.setUser(user);
        return token;
    }
}
