package com.svitsmachnogo.api.dto;

import lombok.Data;

import java.net.URL;

@Data
public class RegistrationUserDTO {

    private String name;

    private String password;

    private String confirmPassword;

    private String email;

    private String redirectTo;
}
