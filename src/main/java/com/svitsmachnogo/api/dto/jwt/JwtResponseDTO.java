package com.svitsmachnogo.api.dto.jwt;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonTypeName("JwtResponse")
public class JwtResponseDTO {
    private String token;
}
