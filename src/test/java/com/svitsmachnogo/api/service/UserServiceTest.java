package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.UserRepository;
import com.svitsmachnogo.api.domain.entity.Role;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    User user = new User();

    Role role = new Role();

    @BeforeEach
    public void createTestUser(){
        role.setId(1);
        role.setName("USER ROLE");
        user.setId(1L);
        user.setEmail("user@mail.com");
        user.setPassword("123");
        user.setName("Bob");
        user.setRoles(List.of(role));
    }

    @Test
    public void loadUserByUsernameIfUserNotFoundThrowException(){

        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(user.getEmail()));
    }

    @Test
    public void loadUserByUsernameThrowExceptionWithEmail(){

        Mockito.when(userRepository.findByEmail("cleanBob@mail.com")).thenReturn(Optional.empty());

        String message = "";
        try{
            userService.loadUserByUsername("cleanBob@mail.com");
        }catch (UsernameNotFoundException e){
           message =  e.getMessage();
        }

        Assertions.assertTrue(message.contains("cleanBob@mail.com"));
    }

    @Test
    public void loadUserByUsernameReturnUserDetails(){
        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(user));

        Role adminRole = new Role();
        adminRole.setName("ADMIN ROLE");
        user.setRoles(List.of(role, adminRole));

        UserDetails userDetails = userService.loadUserByUsername("user@mail.com");

        Assertions.assertEquals(user.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(user.getPassword(), userDetails.getPassword());
        Assertions.assertLinesMatch(user.getRoles().stream().map(Role::getName),
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority));

    }

    @Test
    public void createNewUserThrowExceptionWhenUserExist() {

        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(user));

        Assertions.assertThrows(UserAlreadyExistException.class, () -> userService.createNewUser(user));
    }

    @SneakyThrows
    @Test
    public void createNewUserSaveUserWhenUserDoseNotExist() {

        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.empty());

        userService.createNewUser(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    //todo: crate tests for private methods

}
