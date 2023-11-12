package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.JwtRequestDTO;
import com.svitsmachnogo.api.dto.JwtResponseDTO;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import com.svitsmachnogo.api.exceptions.DifferentPasswordsExceptions;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import com.svitsmachnogo.api.service.abstractional.AuthService;
import com.svitsmachnogo.api.service.abstractional.MailSenderService;
import com.svitsmachnogo.api.service.abstractional.UserService;
import com.svitsmachnogo.api.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtils jwtTokenUtils;

    private final MailSenderService mailSenderService;

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
    public ResponseEntity<JwtResponseDTO> createJwtForUser(JwtRequestDTO authRequest) throws BadCredentialsException {
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
     * @throws UserAlreadyExistException    If a user with the same name already exists.
     * @author Vanya Demydenko
     */
    public ResponseEntity<?> registration(RegistrationUserDTO userDTO) throws DifferentPasswordsExceptions, UserAlreadyExistException {
        validate(userDTO);
        mailSenderService.sendMail(buildConfirmMessage(userDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Handles the user confirmation process using a JWT (JSON Web Token).
     * This method extracts user information from a JWT, validates the email, creates a new user,
     * and generates a JWT token for the newly created user. It ensures that the email is unique
     * to avoid duplicate user registrations.
     *
     * @param jwtUser The JWT string containing user information for confirmation.
     * @return ResponseEntity containing the result of the confirmation process.
     * @throws UserAlreadyExistException If a user with the specified email already exists.
     * @author Vanya Demydenko
     * @see JwtTokenUtils#parseUserFromJwt(String)
     * @see #validateEmail(RegistrationUserDTO, String)
     * @see UserService#createNewUser(RegistrationUserDTO)
     * @see UserService#convertToUserDetails(User)
     */
    public ResponseEntity<?> confirmation(String jwtUser) throws UserAlreadyExistException {
        RegistrationUserDTO userDTO = jwtTokenUtils.parseUserFromJwt(jwtUser);
        validateEmail(userDTO, "User with '%s' email already exist!!! You can't to use confirmation link twice.");
        User user = userService.createNewUser(userDTO);
        return ResponseEntity.ok(generateTokenByUserDetails(userService.convertToUserDetails(user)));
    }

    /**
     * Generates a JWT token based on the provided UserDetails and encapsulates it in a JwtResponseDTO.
     *
     * @param userDetails The UserDetails object used to generate the JWT token.
     * @return {@link JwtResponseDTO} containing the generated JWT token.
     * @author Vanya Demydenko
     */
    private JwtResponseDTO generateTokenByUserDetails(UserDetails userDetails) {
        return new JwtResponseDTO(jwtTokenUtils.generateToken(userDetails));
    }


    /**
     * Verifies the provided RegistrationUserDTO to ensure password consistency and user uniqueness.
     *
     * @param userDTO The RegistrationUserDTO to be verified.
     * @throws DifferentPasswordsExceptions If the passwords do not match.
     * @throws UserAlreadyExistException    If a user with the same email already exists.
     * @author Vanya Demydenko
     */
    private void validate(RegistrationUserDTO userDTO) throws UserAlreadyExistException, DifferentPasswordsExceptions {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new DifferentPasswordsExceptions("Different password");
        }
        validateEmail(userDTO, "User with '%s' email already exist!!!");
    }

    /**
     * Validates if a user with the specified email already exists and throws an exception if found.
     *
     * @param userDTO The RegistrationUserDTO containing user information to be validated.
     * @param format  The format of the error message if the user already exists.
     * @throws UserAlreadyExistException If a user with the specified email already exists.
     * @author Vanya Demydenko
     */
    private void validateEmail(RegistrationUserDTO userDTO, String format) throws UserAlreadyExistException {
        if (isExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException(String.format(format, userDTO.getEmail()));
        }
    }

    /**
     * Checks if a user with the specified email already exists.
     *
     * @param email The email to be checked for existence.
     * @return True if a user with the specified email exists, false otherwise.
     * @author Vanya Demydenko
     */
    private boolean isExist(String email) {
        return userService.findByEmail(email).isPresent();
    }


    /**
     * Builds a confirmation email message for the user registration.
     *
     * @param userDTO The RegistrationUserDTO containing user information.
     * @return A SimpleMailMessage object representing the confirmation email message.
     * @author Vanya Demydenko
     */
    private SimpleMailMessage buildConfirmMessage(RegistrationUserDTO userDTO) {
        return mailSenderService.createMessage(userDTO.getEmail(),
                "Підтвердження реєстрації",
                "Привіт " + userDTO.getName() +
                        "!\nДля підтвердження реєстрації натисни тут " + createConfirmLink(userDTO) +
                        "\n Це посилання стане неактивним через 1 годину (до " + generateDateOfExpiredLink() + ").");
    }

    /**
     * Generates the expiration date and time for the confirmation link.
     *
     * @return A formatted string representing the expiration date and time.
     * @author Vanya Demydenko
     */
    private static String generateDateOfExpiredLink() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm:ss");
        return formatter.format(LocalDateTime.now().plusHours(1));
    }

    /**
     * Creates a confirmation link for the user registration.
     *
     * @param registrationUserDTO The RegistrationUserDTO containing user information.
     * @return The confirmation link as a string.
     * @author Vanya Demydenko
     */
    private String createConfirmLink(RegistrationUserDTO registrationUserDTO) {
        String encryptUser = jwtTokenUtils.generateConfirmKey(registrationUserDTO);
        String redirectTo = registrationUserDTO.getRedirectTo();
        return concatUrl(encryptUser, redirectTo);
    }

    /**
     * Concatenates the confirmation link components into a full URL.
     *
     * @param encryptUser The encrypted user information for confirmation.
     * @param redirectTo  The base URL to which the confirmation link will be appended.
     * @return The full confirmation link URL as a string.
     * @author Vanya Demydenko
     */
    private static String concatUrl(String encryptUser, String redirectTo) {
        return (redirectTo.endsWith("/")) ? (redirectTo + encryptUser) : (redirectTo + "/" + encryptUser);
    }

}
