package com.QuickCommerce.User.Models;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token extends Base{
    private String value;
    private Date expiryDate;
    private Boolean isDeleted;
    @ManyToOne
    private User user;
}
