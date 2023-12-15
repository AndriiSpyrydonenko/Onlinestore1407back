package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void findByIdShouldReturnCart(){
        Cart cart = cartRepository.findById(1L).orElse(null);

        Assertions.assertNotNull(cart);

    }

    @Test
    public void findByIdShouldReturnCartWithSpecifyId(){
        Cart cart = cartRepository.findById(1L).orElse(new Cart());

        Assertions.assertEquals(1L, cart.getId());

    }
}