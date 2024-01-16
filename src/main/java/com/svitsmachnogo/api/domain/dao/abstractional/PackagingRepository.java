package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.packaging.Packaging;
import com.svitsmachnogo.api.domain.entity.packaging.PackagingId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Packaging entities.
 *
 * @author Vanya Demydenko
 */
public interface PackagingRepository extends JpaRepository<Packaging, PackagingId> {
}
