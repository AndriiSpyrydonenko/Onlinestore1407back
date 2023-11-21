package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.dto.jwt.JwtRequestDTO;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.exceptions.DifferentPasswordsExceptions;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> createJwtForUser(JwtRequestDTO authRequest);

    ResponseEntity<?> registration(RegistrationUserDTO userDTO)
            throws DifferentPasswordsExceptions, UserAlreadyExistException;

    ResponseEntity<?> confirmation(String jwtUser) throws UserAlreadyExistException;

}
