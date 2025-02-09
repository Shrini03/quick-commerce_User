package com.QuickCommerce.User.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends Base{
    String name;
    String email;
    String hashedPassword;
    @ManyToMany(mappedBy = "user", fetch = FetchType.EAGER)

    List<Role> roles;
}
