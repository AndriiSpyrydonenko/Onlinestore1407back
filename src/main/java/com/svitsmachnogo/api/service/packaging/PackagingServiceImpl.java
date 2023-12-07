package com.svitsmachnogo.api.service.packaging;

import com.svitsmachnogo.api.domain.dao.abstractional.PackagingRepository;
import com.svitsmachnogo.api.domain.entity.Packaging;
import com.svitsmachnogo.api.domain.entity.PackagingId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of PackagingService providing operations related to packaging entities.
 * This service implements the method to retrieve packaging details by their identifiers.
 *
 * @author Vanya Demydenko
 */
@Service
@RequiredArgsConstructor
public class PackagingServiceImpl implements PackagingService {

    private final PackagingRepository packagingRepository;

    /**
     * Retrieves packaging details by their unique identifier.
     *
     * @param packagingId The unique identifier of the packaging.
     * @return An Optional containing the Packaging entity if found, otherwise an empty Optional.
     */
    @Override
    public Optional<Packaging> findById(PackagingId packagingId) {
        return packagingRepository.findById(packagingId);
    }
}
