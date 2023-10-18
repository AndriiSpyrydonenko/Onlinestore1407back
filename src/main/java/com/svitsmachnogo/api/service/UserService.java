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

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private final RoleService roleService;

    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with '%s' email not found!!!", username)
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
//        repository.findByEmail(user.getEmail()).ifPresent( u -> exist.set(true));
        //todo: add user
    }

    private void throwException(String email) throws UserAlreadyExistException {
        throw new UserAlreadyExistException(String.format("User with '%s' email already exist!!!", email));
    }
}

