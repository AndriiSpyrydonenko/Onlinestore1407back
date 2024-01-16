package com.svitsmachnogo.api.service.packaging;

import com.svitsmachnogo.api.domain.entity.packaging.Packaging;
import com.svitsmachnogo.api.domain.entity.packaging.PackagingId;

import java.util.Optional;

/**
 * Service providing operations related to packaging entities.
 * This service defines methods to retrieve packaging details by their identifiers.
 *
 * @author Vanya Demydenko
 */
public interface PackagingService {

    /**
     * Retrieves packaging details by their unique identifier.
     *
     * @param packagingId The unique identifier of the packaging.
     * @return An Optional containing the Packaging entity if found, otherwise an empty Optional.
     */
    Optional<Packaging> findById(PackagingId packagingId);
}
