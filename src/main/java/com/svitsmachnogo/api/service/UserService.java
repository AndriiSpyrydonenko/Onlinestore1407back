package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.UserRepository;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.exceptions.UserAlreadyExistException;
import com.svitsmachnogo.api.service.abstractional.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    private UserDetails loadUserByEmail(String email) {
        User user = findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with '%s' email not found!!!", email)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createNewUser(User user) throws UserAlreadyExistException {
        if (isExist(user)) {  // if such a user exist , throw an exception
            throwException(user.getEmail());
        }
        user.setRoles(List.of(roleService.findByName("ROLE_USER").get()));
        userRepository.save(user);
    }

    private boolean isExist(User user) {
        return userRepository.findByEmail(user.getEmail()).isPresent();
    }

    private void throwException(String email) throws UserAlreadyExistException {
        throw new UserAlreadyExistException(String.format("User with '%s' email already exist!!!", email));
    }
}

