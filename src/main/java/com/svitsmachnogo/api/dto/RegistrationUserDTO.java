package com.svitsmachnogo.api.dto;

import lombok.Data;

@Data
public class RegistrationUserDTO {

    private String name;

    private String password;

    private String confirmPassword;

    private String email;
}
