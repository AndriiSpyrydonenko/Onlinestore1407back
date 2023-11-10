package com.svitsmachnogo.api.service.abstractional;


import com.svitsmachnogo.api.domain.entity.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name);
}
