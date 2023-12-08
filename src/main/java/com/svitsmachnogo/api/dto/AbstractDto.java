package com.svitsmachnogo.api.dto;

/**
 * The marker interface for DTO (Data Transfer Object) classes.
 * Implementing classes indicate that they are DTOs used for transferring data
 * between different layers or components of the application.
 * <p>
 * DTOs typically carry data between the frontend and backend, and they often
 * contain a subset of information from domain/entity objects.
 * <p>
 * Implementing this interface helps in identifying classes as DTOs, allowing
 * for unified processing or transformation of entity objects into DTOs.
 * <p>
 * Example usage includes its use in conjunction with the {@link com.svitsmachnogo.api.utils.DtoUtils}
 * class to convert a list of entity objects into a list of DTOs using a {@link com.svitsmachnogo.api.dto.DtoFactory}.
 * The interface itself does not mandate any methods or fields but serves as a
 * common marker interface for DTO classes.
 *
 * @author Vanya Demydenko
 */
public interface AbstractDto {

}
