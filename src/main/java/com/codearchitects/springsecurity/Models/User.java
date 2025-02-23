package com.codearchitects.springsecurity.Models;

import com.codearchitects.springsecurity.Enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class User extends BaseEntity {

    @NotBlank(message = "Email can not be empty")
    @Column(nullable = false, unique = true)
    String email;

    @NotBlank(message = "Password can not be empty")
    @Column(nullable = false)
    String password;

    @NotBlank(message = "Username can not be empty")
    @Column(nullable = false, unique = true)
    String userName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


}
