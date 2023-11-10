package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.entity.Role;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.JwtRequestDTO;
import com.svitsmachnogo.api.dto.JwtResponseDTO;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.exceptions.DifferentPasswordsExceptions;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import com.svitsmachnogo.api.service.abstractional.UserService;
import com.svitsmachnogo.api.utils.JwtTokenUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtils jwtTokenUtils;

    @Mock
    private UserDetails userDetails;

    private org.springframework.security.core.userdetails.User suser;

    private JwtRequestDTO authRequest;

    private RegistrationUserDTO registrationUser;

    private User user;


    @BeforeEach
    public void setup() {
        authRequest = new JwtRequestDTO();
        authRequest.setEmail("user@gmail.com");
        authRequest.setPassword("123");

        suser = new org.springframework.security.core.userdetails.User(
                authRequest.getEmail(),
                authRequest.getPassword(),
                new ArrayList<>()
        );
        registrationUser = new RegistrationUserDTO();
        registrationUser.setName("Bob");
        registrationUser.setEmail("user@gmail.com");
        registrationUser.setPassword("123");
        registrationUser.setConfirmPassword("123");

        Role role = new Role();
        role.setId(1);
        role.setName("ROLE_USER");

        user = new User();
        user.setId(3L);
        user.setPassword("123");
        user.setEmail("user@gmail.com");
        user.setName("Bob");
        user.setRoles(List.of(role));
    }


    @Test
    public void createJwtForUserSuccess() {
        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())))
                .thenReturn(null);
        Mockito.when(userService.loadUserByUsername(authRequest.getEmail())).thenReturn(userDetails);
        Mockito.when(jwtTokenUtils.generateToken(userDetails)).thenReturn("mocked_token");

        ResponseEntity<JwtResponseDTO> response = authService.createJwtForUser(authRequest);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals("mocked_token", Objects.requireNonNull(response.getBody()).getToken());
    }

    @Test
    public void createJwtForUserBadCredentials() {
        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        Assertions.assertThrows(BadCredentialsException.class,
                () -> authService.createJwtForUser(authRequest));
    }

    @SneakyThrows
    @Test
    public void createNewUserShouldThrowsExceptionIfRequestHasDiffPassword() {
        registrationUser.setConfirmPassword("1234");
        Assertions.assertThrows(DifferentPasswordsExceptions.class, () -> authService.registration(registrationUser));
    }

    @SneakyThrows
    @Test
    public void createNewUserShouldThrowsExceptionIfSuchUserExist() {
        Mockito.when(userService.findByEmail("user@gmail.com")).thenReturn(Optional.of(new User()));
        Assertions.assertThrows(UserAlreadyExistException.class, () -> authService.registration(registrationUser));
    }

    @SneakyThrows
    @Test
    public void createNewUserShouldReturnResponseEntity() {
        Mockito.when(userService.findByEmail("user@gmail.com")).thenReturn(Optional.empty());
        Mockito.when(userService.createNewUser(registrationUser)).thenReturn(user);
        Mockito.when(userService.convertToUserDetails(user)).thenReturn(suser);
        Mockito.when(jwtTokenUtils.generateToken(suser)).thenReturn("mocked_token");

        Assertions.assertEquals(200, authService.registration(registrationUser).getStatusCode().value());
        Assertions.assertEquals("mocked_token", Objects.requireNonNull(authService.registration(registrationUser).getBody()).getToken());
    }
}
