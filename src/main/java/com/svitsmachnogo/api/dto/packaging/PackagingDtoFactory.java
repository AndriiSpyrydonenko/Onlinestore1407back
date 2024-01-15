package com.svitsmachnogo.api.dto.packaging;

import com.svitsmachnogo.api.domain.entity.Packaging;
import com.svitsmachnogo.api.dto.DtoFactory;

/**
 * A factory class for creating PackagingDto instances.
 * This class implements the DtoFactory interface for Packaging entities.
 */
public class PackagingDtoFactory extends PackagingDto implements DtoFactory<Packaging> {

    /**
     * Creates a PackagingDto instance based on the provided Packaging entity.
     *
     * @param targetEntity The Packaging entity used to create a PackagingDto.
     * @return The PackagingDto instance created from the provided Packaging entity.
     */
    @Override
    public PackagingDto of(Packaging targetEntity) {
        PackagingDto packagingDto = new PackagingDto();
        packagingDto.setProductId(targetEntity.getId().getProductId());
        packagingDto.setAmount(targetEntity.getId().getAmount());
        packagingDto.setCost(targetEntity.getCost());
        return packagingDto;
    }
}

