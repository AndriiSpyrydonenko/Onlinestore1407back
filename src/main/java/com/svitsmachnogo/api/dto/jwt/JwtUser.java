package com.svitsmachnogo.api.dto.jwt;

import lombok.Data;

@Data
public class JwtUser {
    private String encryptedUser;
}
