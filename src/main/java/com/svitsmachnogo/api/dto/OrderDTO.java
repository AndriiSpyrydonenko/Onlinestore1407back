package com.svitsmachnogo.api.dto;

import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.domain.entity.Packaging;
import com.svitsmachnogo.api.domain.entity.PayType;
import com.svitsmachnogo.api.domain.entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;

    private Long userId;

    private String comment;

    private Timestamp createDate;

    private String payType;

    private List<Packaging> packagingList;

    private OrderDTO() {
    }

    public static OrderDTO of(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.id = order.getId();
        orderDTO.userId = order.getUser().getId();
        orderDTO.comment = order.getComment();
        orderDTO.createDate = order.getCreateDate();
        orderDTO.payType = order.getPayType();
        orderDTO.packagingList = order.getPackagingList();
        return orderDTO;
    }

    public static List<OrderDTO> listOf(List<Order> orders){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orders.forEach(o -> orderDTOList.add(of(o)));
        return orderDTOList;
    }
}
