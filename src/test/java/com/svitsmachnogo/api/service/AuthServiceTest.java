package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private final AuthServiceImpl authService;

    @Mock
    private final UserServiceImpl userService;

    @Mock
    private final AuthenticationManager authenticationManager;

    @Mock
    private final JwtTokenUtils jwtTokenUtils;

}