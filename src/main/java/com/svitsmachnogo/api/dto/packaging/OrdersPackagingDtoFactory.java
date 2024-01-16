package com.svitsmachnogo.api.dto.packaging;

import com.svitsmachnogo.api.domain.entity.packaging.OrdersPackaging;
import com.svitsmachnogo.api.dto.DtoFactory;

/**
 * A factory class for creating OrdersPackagingDto instances.
 * This class implements the DtoFactory interface for OrdersPackaging entities.
 */
public class OrdersPackagingDtoFactory implements DtoFactory<OrdersPackaging> {

    /**
     * Creates a OrdersPackagingDto instance based on the provided OrdersPackaging entity.
     *
     * @param targetEntity The OrdersPackaging entity used to create a OrdersPackagingDto.
     * @return The OrdersPackagingDto instance created from the provided OrdersPackaging entity.
     */
    @Override
    public OrdersPackagingDto of(OrdersPackaging targetEntity) {
        OrdersPackagingDto packagingDto = new OrdersPackagingDto();
        packagingDto.setAmountOfUnits(targetEntity.getAmountOfUnits());
        packagingDto.setProductId(targetEntity.getPackaging().getId().getProductId());
        packagingDto.setAmount(targetEntity.getPackaging().getId().getAmount());
        packagingDto.setCost(targetEntity.getPackaging().getCost());
        return packagingDto;
    }
}
