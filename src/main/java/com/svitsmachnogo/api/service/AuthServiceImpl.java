package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.JwtRequestDTO;
import com.svitsmachnogo.api.dto.JwtResponseDTO;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.exceptions.DifferentPasswordsExceptions;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import com.svitsmachnogo.api.service.abstractional.AuthService;
import com.svitsmachnogo.api.service.abstractional.UserService;
import com.svitsmachnogo.api.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtils jwtTokenUtils;

    /**
     * The method receives a {@link JwtRequestDTO} that contains the user's credentials.
     * If the credentials are OK, this method returns a token wrapped in a {@link JwtResponseDTO}.
     * Throws a {@link BadCredentialsException} in other ways.
     *
     * @param authRequest
     * @return {@link JwtResponseDTO }
     * @throws BadCredentialsException
     * @author Vanya Demydenko
     */
    public ResponseEntity<?> createJwtForUser(JwtRequestDTO authRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        return ResponseEntity.ok(generateTokenByUserDetails(userService.loadUserByUsername(authRequest.getEmail())));
    }

    /**
     * Creates a new user based on the provided data and returns a ResponseEntity
     * with a generated token.
     *
     * @param userDTO The RegistrationUserDTO object containing the new user's data.
     * @return ResponseEntity with the generated token.
     * @throws DifferentPasswordsExceptions If the passwords do not match.
     * @throws UserAlreadyExistException   If a user with the same name already exists.
     * @author Vanya Demydenko
     */
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO userDTO) throws DifferentPasswordsExceptions, UserAlreadyExistException {
        verification(userDTO);
        User user = userService.createNewUser(userDTO);
        return ResponseEntity.ok(generateTokenByUserDetails(userService.convertToUserDetails(user)));
    }

    /**
     * Verifies the provided RegistrationUserDTO to ensure password consistency and user uniqueness.
     *
     * @param userDTO The RegistrationUserDTO to be verified.
     * @throws DifferentPasswordsExceptions If the passwords do not match.
     * @throws UserAlreadyExistException If a user with the same email already exists.
     * @author Vanya Demydenko
     */
    private void verification(RegistrationUserDTO userDTO) throws DifferentPasswordsExceptions, UserAlreadyExistException {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new DifferentPasswordsExceptions("Different password");
        }
        if (isExist(userDTO.getEmail())) {  // if such a user exist , throw an exception
            throw new UserAlreadyExistException(String.format("User with '%s' email already exist!!!", userDTO.getEmail()));
        }
    }

    /**
     * Checks if a user with the given email already exists in the system.
     *
     * @param email The email address to be checked for existence.
     * @return true if a user with the provided email exists, false otherwise.
     * @author Vanya Demydenko
     */
    private boolean isExist(String email) {
        return userService.findByEmail(email).isPresent();
    }

    /**
     * Generates a JWT token based on the provided UserDetails and encapsulates it in a JwtResponseDTO.
     *
     * @param userDetails The UserDetails object used to generate the JWT token.
     * @return {@link JwtResponseDTO} containing the generated JWT token.
     * @author Vanya Demydenko
     */
    private JwtResponseDTO generateTokenByUserDetails(UserDetails userDetails){
       return new JwtResponseDTO(jwtTokenUtils.generateToken(userDetails));
    }

}
