package com.svitsmachnogo.api.dto;

/**
 * A factory interface used for creating DTOs (Data Transfer Objects) from entity objects.
 * Implementations of this interface provide a method, 'of', which is responsible for converting
 * an entity object into a corresponding DTO instance.
 * <p>
 * @param <T> Type parameter representing the target entity class to convert from.
 *
 * @author Vanya Demydenko
 */
public interface DtoFactory<T> {

    /**
     * Creates a DTO instance from the provided target entity.
     *
     * @param targetEntity The entity object to be converted into a DTO.
     * @param <R>          Type parameter representing the resulting DTO class.
     * @return The DTO instance created from the provided entity.
     */
    <R extends AbstractDto> R of(T targetEntity);
}
