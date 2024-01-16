package com.svitsmachnogo.api.dto.order;

import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.dto.order.response_dto.ResponseOrderDtoFactory;
import com.svitsmachnogo.api.exceptions.NoSuchOrderException;

import java.util.Objects;

/**
 * Factory class responsible for creating DTO factories for orders based on the specified FactoryType.
 * It creates either request or response DTO factories for handling order DTO creation.
 *
 * @author Vanya Demydenko
 */
public class OrderDtoFactory {

    /**
     * Creates a DTO factory for orders based on the given FactoryType.
     *
     * @param factoryType The type of factory to be created (REQUEST or RESPONSE).
     * @return A DTO factory instance for handling order DTO creation.
     * @throws NoSuchOrderException if the specified FactoryType is not recognized or does not exist.
     */
    public static DtoFactory<Order> crateFactory(FactoryType factoryType) throws NoSuchOrderException {
        Objects.requireNonNull(factoryType);

        if (factoryType.equals(FactoryType.RESPONSE)) {
            return new ResponseOrderDtoFactory();
        } else {
            throw new NoSuchOrderException(String.format("Factory with type: %s does not exist", factoryType.name()));
        }
    }
}
