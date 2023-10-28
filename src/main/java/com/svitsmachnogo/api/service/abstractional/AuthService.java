package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.dto.JwtRequestDTO;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.exceptions.DifferentPasswordsExceptions;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> createJwtForUser(JwtRequestDTO authRequest);

    ResponseEntity<?> createNewUser(RegistrationUserDTO userDTO)
            throws DifferentPasswordsExceptions, UserAlreadyExistException;

}
