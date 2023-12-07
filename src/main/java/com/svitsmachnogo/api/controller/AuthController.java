package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.dto.jwt.JwtRequestDTO;
import com.svitsmachnogo.api.dto.jwt.JwtResponseDTO;
import com.svitsmachnogo.api.dto.jwt.JwtUser;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Tag(name = "Auth controller")
public class AuthController {

    private final AuthService authService;

    private final String authHeaderDescription = "This endpoint is used to authenticate a user and receive an " +
            "authorization token (JWT) if the request is valid.The token must be included in the HTTP request " +
            "header with the name 'Authorization', and it should be prefixed with 'Bearer ' followed by " +
            "a space (e.g., 'Bearer your_JWT_token_here'). The token is considered valid for testing purposes " +
            "for 5 minute after issuance.)";

    private final String registrationDescription = "The endpoint retrieve the user in the request body and redirect URL." +
            " Returns http status only";

    private final String confirmationDescription = "The endpoint retrieve the encrypted part of URL in the request body " +
            ".Returns a JWT that lives 5m or an AppError";


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

    @Operation(summary = "Registration", description = registrationDescription)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok, confirm link sent to email", content =
                    {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "409", description = "Such user exist!", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppError.class))}),
            @ApiResponse(responseCode = "400", description = "Confirm password is wrong!", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppError.class))})
    })
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationUserDTO registrationUserDTO) throws DifferentPasswordsExceptions, UserAlreadyExistException {
        return authService.registration(registrationUserDTO);
    }

    @Operation(summary = "Email confirmation, and storage user in db", description = confirmationDescription)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok, user confirmed his email.", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = JwtResponseDTO.class))}),
            @ApiResponse(responseCode = "409", description = "Such user exist!", content =
                    {@Content(mediaType = "application/json", schema =
                    @Schema(implementation = AppError.class))}),
    })
    @PostMapping("/confirmEmail")
    public ResponseEntity<?> confirm(@RequestBody JwtUser jwtUser) throws UserAlreadyExistException {
        return authService.confirmation(jwtUser.getEncryptedUser());
    }
}
