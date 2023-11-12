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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


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

    @Mock
    private MailSenderServiceImpl mailSenderService;

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
        registrationUser.setRedirectTo("/link");

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
    public void registrationShouldThrowsExceptionIfRequestHasDiffPassword() {
        registrationUser.setConfirmPassword("1234");
        Assertions.assertThrows(DifferentPasswordsExceptions.class, () -> authService.registration(registrationUser));
    }

    @SneakyThrows
    @Test
    public void registrationShouldThrowsExceptionIfSuchUserExist() {
        Mockito.when(userService.findByEmail("user@gmail.com")).thenReturn(Optional.of(new User()));
        Assertions.assertThrows(UserAlreadyExistException.class, () -> authService.registration(registrationUser));
    }

    @SneakyThrows
    @Test
    public void registrationShouldCallSendMailOnce() {

        authService.registration(registrationUser);

        Mockito.verify(mailSenderService, times(1))
                .sendMail(any());
    }

    @SneakyThrows
    @Test
    public void registrationShouldReturnResponseEntityWithOKStatus() {
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(HttpStatus.OK);
        ResponseEntity<?> actualResponse = authService.registration(registrationUser);

        Assertions.assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }

    @Test
    public void registrationShouldSendValidMessage() {

        Mockito.when(jwtTokenUtils.generateConfirmKey(registrationUser)).thenReturn("encryptedUser");
        Mockito.when(mailSenderService.createMessage(any(), any(), any())).thenCallRealMethod();

        String expectedText = "Привіт " + registrationUser.getName() +
                "!\nДля підтвердження реєстрації натисни тут " + "/link/encryptedUser" +
                "\n Це посилання стане неактивним через 1 годину" +
                " (до " + timeByPattern(LocalDateTime.now().plusHours(1)) + ").";

        SimpleMailMessage actualMessage = buildConfirmMessage(registrationUser);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(registrationUser.getEmail());
        expectedMessage.setSubject("Підтвердження реєстрації");
        expectedMessage.setText(expectedText);


        Assertions.assertEquals(Arrays.stream(expectedMessage.getTo()).findFirst().get(), Arrays.stream(actualMessage.getTo()).findFirst().get());
        Assertions.assertEquals(expectedMessage.getSubject(), actualMessage.getSubject());
        Assertions.assertEquals(expectedMessage.getText(), actualMessage.getText());
    }

    @Test
    public void confirmationShouldThrowExceptionIfUserExist() {
        Mockito.when(jwtTokenUtils.parseUserFromJwt(any())).thenReturn(registrationUser);
        Mockito.when(userService.findByEmail(registrationUser.getEmail())).thenReturn(Optional.of(new User()));

        Assertions.assertThrows(UserAlreadyExistException.class,
                () -> authService.confirmation("encrypted user"));
    }

    @Test
    public void confirmationShouldThrowExceptionWithUserEmail() {
        Mockito.when(jwtTokenUtils.parseUserFromJwt(any())).thenReturn(registrationUser);
        Mockito.when(userService.findByEmail(registrationUser.getEmail())).thenReturn(Optional.of(new User()));
        String exceptionsMessage = "";

        try {
            authService.confirmation("encrypted user");
        } catch (UserAlreadyExistException e) {
            exceptionsMessage = e.getMessage();
        }

        Assertions.assertTrue(exceptionsMessage.contains(registrationUser.getEmail()));
    }

    @SneakyThrows
    @Test
    public void confirmationShouldReturnJwtInResponseEntity() {
        Mockito.when(jwtTokenUtils.parseUserFromJwt(any())).thenReturn(registrationUser);
        Mockito.when(userService.findByEmail(registrationUser.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userService.createNewUser(registrationUser)).thenReturn(user);
        Mockito.when(jwtTokenUtils.generateToken(any())).thenReturn("someToken");

        ResponseEntity<?> response = authService.confirmation("encryptedUser");

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("someToken", ((JwtResponseDTO) response.getBody()).getToken());
    }

    @SneakyThrows
    private SimpleMailMessage buildConfirmMessage(RegistrationUserDTO userDTO) {
        Method method = AuthServiceImpl.class.getDeclaredMethod("buildConfirmMessage", RegistrationUserDTO.class);
        method.setAccessible(true);
        return (SimpleMailMessage) method.invoke(authService, userDTO);
    }

    private String timeByPattern(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm:ss");
        return formatter.format(dateTime);
    }
}
