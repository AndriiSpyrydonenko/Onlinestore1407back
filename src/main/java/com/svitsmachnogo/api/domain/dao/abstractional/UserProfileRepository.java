package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing UserProfile entities.
 *
 * @author Vanya Demydenko
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
