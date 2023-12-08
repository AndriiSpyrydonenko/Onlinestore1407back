package com.svitsmachnogo.api.dto.order.request_dto;

import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.order.OrderDto;

/**
 * Represents an order data transfer object (DTO) used for requests.
 * This class extends the abstract class {@link OrderDto} to inherit common order-related fields.
 * It serves as a specific type of order DTO designed for handling requests.
 *
 * @see AbstractDto
 * @see OrderDto
 */
public class RequestOrderDto extends OrderDto implements AbstractDto {
}

