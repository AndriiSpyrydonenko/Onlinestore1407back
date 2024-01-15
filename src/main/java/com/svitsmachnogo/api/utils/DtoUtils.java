package com.svitsmachnogo.api.utils;

import com.svitsmachnogo.api.domain.entity.*;
import com.svitsmachnogo.api.dto.AbstractDto;
import com.svitsmachnogo.api.dto.DtoFactory;
import com.svitsmachnogo.api.dto.order.OrderDto;
import com.svitsmachnogo.api.dto.packaging.PackagingDto;
import com.svitsmachnogo.api.dto.product.ProductDtoForCart;
import com.svitsmachnogo.api.dto.product.ProductDtoForCartFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Utility class for data mapping and conversion operations related to DTOs (Data Transfer Objects).
 * Provides methods for converting entities to DTOs and vice versa.
 * Also offers functionalities for mapping data from DTOs to specific entity types.
 * This class simplifies the process of data conversion and mapping in various contexts.
 *
 * <p>This utility allows convenient conversion and mapping operations, facilitating the
 * handling of DTOs and their transformations in software development projects.
 */
public class DtoUtils {

    /**
     * Creates a list of DTOs based on a list of objects using a factory for conversion.
     *
     * @param <T>         The type of target entities.
     * @param <R>         The type of DTOs to be created.
     * @param entities    The list of objects to convert.
     * @param dtoFactory  The factory responsible for converting objects to DTOs.
     * @return The list of created DTOs.
     */
    public static <T, R extends AbstractDto> List<R> listOf(List<T> entities, DtoFactory<T> dtoFactory) {
        List<R> rList = new ArrayList<>();
        entities.forEach(o -> rList.add(dtoFactory.of(o)));
        return rList;
    }

    /**
     * Converts a list of Packaging objects into a list of ProductDtoForCart objects.
     *
     * @param packagingList The list of Packaging objects to convert.
     * @return The list of {@link ProductDtoForCart} objects.
     */
    public static List<ProductDtoForCart> cartProductsListOf(List<Packaging> packagingList){
        return packagingList
                .stream()
                .map(p -> createDto(p, p.getProduct()))
                .collect(Collectors.toList());
    }

    /**
     * Creates a ProductDtoForCart based on Packaging and Product objects.
     */
    private static ProductDtoForCart createDto(Packaging packaging , Product product){
        return new ProductDtoForCartFactory(packaging).of(product);
    }

    /**
     * Maps the provided data from OrderDto to create an Order entity.
     *
     * @param user      The UserProfile associated with the order (can be null).
     * @param orderDTO  The OrderDto containing details to create an order.
     * @return The created Order entity.
     */
    public static Order mapToOrder(UserProfile user, OrderDto orderDTO){
        Order order = new Order();
        order.setUserProfile(user);
        order.setComment(orderDTO.getComment());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerSurname(orderDTO.getCustomerSurname());
        order.setCustomerPhoneNumber(orderDTO.getCustomerPhoneNumber());
        order.setCustomerAddress(orderDTO.getCustomerAddress());
        order.setPayType(orderDTO.getPayType());
        order.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));

        order.setPackagingList(orderDTO.getPackagingList()
                .stream()
                .map(DtoUtils::mapToPackaging)
                .collect(Collectors.toList()));

        order.setTotalCost(order.getPackagingList()
                .stream()
                .mapToDouble(Packaging::getCost)
                .sum());

        return order;
    }

    /**
     * Maps PackagingDto to create Packaging entities.
     *
     * @param packagingDto The PackagingDto containing packaging details.
     * @return The created Packaging entity.
     */
    public static Packaging mapToPackaging(PackagingDto packagingDto){
        Packaging packaging = new Packaging();
        PackagingId id = new PackagingId();
        id.setProductId(packagingDto.getProductId());
        id.setAmount(packagingDto.getAmount());
        packaging.setId(id);
        packaging.setCost(packagingDto.getCost());
        return packaging;
    }

}

