package com.codearchitects.springsecurity.DTOs;


import lombok.Data;

@Data
public class SignInRequestDTO {

    String userName;
    String password;
}
