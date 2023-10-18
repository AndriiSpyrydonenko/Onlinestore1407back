package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByEmail(String email);
}
