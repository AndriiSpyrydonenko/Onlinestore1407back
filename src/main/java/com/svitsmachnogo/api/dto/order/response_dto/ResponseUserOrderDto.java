package com.svitsmachnogo.api.dto.order.response_dto;

import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.order.OrderDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * Represents a response data transfer object (DTO) for an order.
 * Extends the OrderDto class and implements the AbstractDto interface.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseUserOrderDto extends OrderDto implements AbstractDto {

    private Long id;

    private Long userId;

    private Double totalCost;

    private Timestamp createDate;

}
