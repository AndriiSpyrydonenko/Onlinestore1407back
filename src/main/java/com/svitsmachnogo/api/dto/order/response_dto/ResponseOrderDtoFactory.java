package com.svitsmachnogo.api.dto.order.response_dto;

import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.dto.order.OrderDto;
import com.svitsmachnogo.api.dto.packaging.PackagingDtoFactory;
import com.svitsmachnogo.api.utils.DtoUtils;

/**
 * A factory class implementing DtoFactory to create ResponseOrderDto objects from Order entities.
 */
public class ResponseOrderDtoFactory implements DtoFactory<Order> {

    /**
     * Converts an Order entity to a ResponseOrderDto object.
     *
     * @param targetEntity The Order entity to be converted.
     * @return A ResponseOrderDto object generated from the provided Order entity.
     */
    @Override
    public OrderDto of(Order targetEntity){
        ResponseOrderDto orderDTO = new ResponseOrderDto();
        orderDTO.setId(targetEntity.getId());
        orderDTO.setTotalCost(targetEntity.getTotalCost());
        orderDTO.setCreateDate(targetEntity.getCreateDate());
        orderDTO.setUserId((targetEntity.getUserProfile() != null) ? targetEntity.getUserProfile().getId() : null);
        orderDTO.setComment(targetEntity.getComment());
        orderDTO.setCustomerName(targetEntity.getCustomerName());
        orderDTO.setCustomerSurname(targetEntity.getCustomerSurname());
        orderDTO.setCustomerAddress(targetEntity.getCustomerAddress());
        orderDTO.setCustomerPhoneNumber(targetEntity.getCustomerPhoneNumber());
        orderDTO.setPayType(targetEntity.getPayType());
        orderDTO.setPackagingList(DtoUtils.listOf(targetEntity.getPackagingList(), new PackagingDtoFactory()));
        return orderDTO;
    }
}

