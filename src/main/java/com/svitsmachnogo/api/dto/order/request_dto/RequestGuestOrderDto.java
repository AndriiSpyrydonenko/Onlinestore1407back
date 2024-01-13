package com.svitsmachnogo.api.dto.order.request_dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.order.OrderDto;
import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Represents an order data transfer object (DTO) used for requests for unauthorised user.
 * This class extends the abstract class {@link OrderDto} to inherit common order-related fields.
 * It serves as a specific type of order DTO designed for handling requests.
 *
 * @see AbstractDto
 * @see OrderDto
 */
public class RequestGuestOrderDto extends OrderDto implements AbstractDto {

    @JsonIgnore
    @Schema(hidden = true)
    private Long userId;
}
