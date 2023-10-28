package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.UserRepository;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.service.abstractional.RoleService;
import com.svitsmachnogo.api.service.abstractional.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    /**
     * Retrieves a user from the repository based on their email address.
     *
     * @param email The email address of the user to be retrieved.
     * @return An {@link Optional} containing the user if found, or an empty Optional if not found.
     * @author Vanya Demydenko
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * Load a UserDetails object by the given username (which is typically an email).
     *
     * @param username The username (typically an email) of the user to be loaded.
     * @return The {@link UserDetails} object representing the user.
     * @throws UsernameNotFoundException If the user with the given username is not found.
     * @author Vanya Demydenko
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    /**
     * Load a User object by the given email address, and convert it to UserDetails.
     *
     * @param email The email address of the user to be loaded.
     * @return {@link UserDetails} object representing the user.
     * @author Vanya Demydenko
     */
    private UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with '%s' email not found!!!", email)
        ));
        return convertToUserDetails(user);
    }

    /**
     * Creates a new user based on the provided data and returns a ResponseEntity
     * with a generated token.
     *
     * @param userDTO The {@link RegistrationUserDTO} object containing the new user's data.
     * @return ResponseEntity with the generated token.
     * @author Vanya Demydenko
     */
    public User createNewUser(RegistrationUserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(List.of(roleService.findByName("ROLE_USER").orElseThrow(NoSuchElementException::new)));
        return userRepository.save(user);
    }

    /**
     * Converts a User object to a Spring Security UserDetails object.
     *
     * @param user The {@link User} object to be converted to UserDetails.
     * @return A {@link org.springframework.security.core.userdetails.User} object representing the user.
     * @author Vanya Demydenko
     */
    public org.springframework.security.core.userdetails.User convertToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

}

