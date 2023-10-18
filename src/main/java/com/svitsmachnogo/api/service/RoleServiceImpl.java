package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.RoleRepository;
import com.svitsmachnogo.api.domain.entity.Role;
import com.svitsmachnogo.api.service.abstractional.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }
}
