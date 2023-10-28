package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.UserRepository;
import com.svitsmachnogo.api.domain.entity.Role;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.service.abstractional.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private PasswordEncoder encoder;

    private  final User user = new User();

    private final Role userRole = new Role();

    private final Role adminRole = new Role();

    private final RegistrationUserDTO userDTO = new RegistrationUserDTO();

    @BeforeEach
    public void createTestUser() {
        userRole.setId(1);
        userRole.setName("ROLE_USER");
        user.setId(1L);
        user.setEmail("user@mail.com");
        user.setPassword("123");
        user.setName("Bob");
        user.setRoles(List.of(userRole));
    }


    private void createTestAdmin() {
        adminRole.setId(2);
        adminRole.setName("ROLE_ADMIN");
        user.setId(2L);
        user.setEmail("admin@mail.com");
        user.setPassword("123");
        user.setName("Tom");
        user.setRoles(List.of(userRole, adminRole));
    }

    private void createUserDTO(){
        userDTO.setName("Bob");
        userDTO.setEmail("user@mail.com");
        userDTO.setPassword("123");
        userDTO.setConfirmPassword("123");
    }

    @Test
    public void findByEmailIfNoSuchUserExist() {
        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.empty());

        Optional<User> userOptional = userService.findByEmail("user@mail.com");
        Assertions.assertFalse(userOptional.isPresent());
    }

    @Test
    public void findByEmailIfUserExist() {
        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(user));

        Optional<User> userOptional = userService.findByEmail("user@mail.com");
        Assertions.assertTrue(userOptional.isPresent());
    }

    @Test
    public void loadUserByUsernameIfUserNotFoundThrowsException() {

        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(user.getEmail()));
    }

    @Test
    public void loadUserByUsernameThrowsExceptionWithEmail() {

        Mockito.when(userRepository.findByEmail("cleanBob@mail.com")).thenReturn(Optional.empty());

        String message = "";
        try {
            userService.loadUserByUsername("cleanBob@mail.com");
        } catch (UsernameNotFoundException e) {
            message = e.getMessage();
        }

        Assertions.assertTrue(message.contains("cleanBob@mail.com"));
    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserHasOneRole() {
        Mockito.when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("user@mail.com");

        Assertions.assertEquals("user@mail.com", userDetails.getUsername());
        Assertions.assertEquals("123", userDetails.getPassword());
        Assertions.assertEquals("ROLE_USER",
                userDetails.getAuthorities().stream().findFirst().get().toString());

    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserHasTwoRole() {
        createTestAdmin();

        Mockito.when(userRepository.findByEmail("admin@mail.com")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("admin@mail.com");

        assertionsWhenUserHasTwoRoles(userDetails);
    }

    @Test
    public void convertToUserDetailsShouldReturnSameUserDetails(){
        UserDetails userDetails = userService.convertToUserDetails(user);

        Assertions.assertEquals("user@mail.com", userDetails.getUsername());
        Assertions.assertEquals("123", userDetails.getPassword());
        Assertions.assertEquals("ROLE_USER",
                userDetails.getAuthorities().stream().findFirst().get().toString());
    }

    @Test
    public void convertToUserDetailsShouldReturnSameUserDetailsWhenUserHasTwoRoles(){
        createTestAdmin();

        UserDetails userDetails = userService.convertToUserDetails(user);

        assertionsWhenUserHasTwoRoles(userDetails);
    }

    private static void assertionsWhenUserHasTwoRoles(UserDetails userDetails) {
        String[] roles = {"ROLE_ADMIN", "ROLE_USER"};
        Object[] grantAuthorities = userDetails.getAuthorities().toArray();

        Assertions.assertEquals("admin@mail.com", userDetails.getUsername());
        Assertions.assertEquals("123", userDetails.getPassword());
        for (int i = 0; i < roles.length; i++) {
            Assertions.assertEquals(roles[i], grantAuthorities[i].toString());
        }
    }

    @Test
    public void createNewUserShouldReturnSavedUser(){
        createUserDTO();
        user.setId(null);

        Mockito.when(encoder.encode(userDTO.getPassword())).thenReturn("123");
        Mockito.when(roleService.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User returnedUser = userService.createNewUser(userDTO);

        Assertions.assertEquals(user ,returnedUser );

    }

    @Test
    public void createNewUserShouldThrowsExceptionIfRoleDoseNotExist(){

        createUserDTO();

        Mockito.when(encoder.encode(userDTO.getPassword())).thenReturn("123");
        Mockito.when(roleService.findByName("ROLE_USER")).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.createNewUser(userDTO));
    }

}
