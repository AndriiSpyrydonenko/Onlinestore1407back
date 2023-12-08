package com.svitsmachnogo.api.dto.order;

import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import lombok.Data;

import java.util.List;

/**
 * An abstract class representing common fields among different order-related DTOs.
 * Classes extending this abstract class inherit common attributes related to orders.
 *
 * @author Vanya Demydenko
 */
@Data
public abstract class OrderDto implements AbstractDto {

    Long userId;

    String comment;

    String customerName;

    String customerSurname;

    String customerPhoneNumber;

    String customerAddress;

    String payType;

    List<PackagingDto> packagingList;

    protected OrderDto(){

    }
}
