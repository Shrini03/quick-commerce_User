package com.QuickCommerce.User.Repos;

import com.QuickCommerce.User.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findTokenByValue(String value);
}
