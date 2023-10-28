package com.svitsmachnogo.api.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

@Data
@JsonTypeName("JwtRequest")
public class JwtRequestDTO {
    private String email;

    private String password;
}
