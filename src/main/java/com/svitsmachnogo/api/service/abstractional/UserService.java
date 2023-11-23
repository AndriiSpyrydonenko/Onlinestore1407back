package com.svitsmachnogo.api.service.abstractional;

import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.RegistrationUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User createNewUser(RegistrationUserDTO userDTO);

    org.springframework.security.core.userdetails.User convertToUserDetails(User user);

}
