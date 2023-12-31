package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.dto.JwtRequestDTO;
import com.svitsmachnogo.api.dto.JwtResponseDTO;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.exceptions.AppError;
import com.svitsmachnogo.api.exceptions.DifferentPasswordsExceptions;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import com.svitsmachnogo.api.service.abstractional.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    private final String authHeaderDescription = "This endpoint is used to authenticate a user and receive an " +
            "authorization token (JWT) if the request is valid.The token must be included in the HTTP request " +
            "header with the name 'Authorization', and it should be prefixed with 'Bearer ' followed by " +
            "a space (e.g., 'Bearer your_JWT_token_here'). The token is considered valid for testing purposes " +
            "for 1 minute after issuance.)";


    @Operation(summary = "Authenticate User and Get JWT Token", description = authHeaderDescription)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = JwtResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Incorrect login or password!", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppError.class))})
    })
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        return authService.createJwtForUser(authRequest);
    }

    @Operation(summary = "Registration", description = "The endpoint retrieve the user in the request body and returns a JWT that lives 1m or an AppError")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = JwtResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = "Such user exist!", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppError.class))}),
            @ApiResponse(responseCode = "400", description = "Confirm password is wrong!", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppError.class))})
    })
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDTO) throws DifferentPasswordsExceptions, UserAlreadyExistException {
        return authService.createNewUser(registrationUserDTO);
    }
}
