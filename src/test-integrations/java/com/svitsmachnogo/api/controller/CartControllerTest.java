package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.InitSQL;
import com.svitsmachnogo.api.domain.entity.Cart;
import com.svitsmachnogo.api.domain.entity.Packaging;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.dto.cart.CartRequestDTO;
import com.svitsmachnogo.api.service.abstractional.UserService;
import com.svitsmachnogo.api.utils.JwtTokenUtils;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private String token;

    @BeforeEach
    void setup(){
        User user = entityManager.find(User.class, 1L);

        token = "Bearer " + jwtTokenUtils.generateToken(userService.convertToUserDetails(user));
    }

    @Test
    @InitSQL
    void addToCart_should_return_401_error_if_user_has_not_JWT_in_headers() throws Exception {
        String json = createCartRequestJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/secure/cart/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    void removeFromCart_should_return_401_error_if_user_has_not_JWT_in_headers() throws Exception {
        String json = createCartRequestJson();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/secure/cart/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    @Test
    @InitSQL
    void getCartByUserId_should_return_401_error_if_user_has_not_JWT_in_headers() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/secure/cart/1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    void addToCart_should_return_code_200_if_user_has_JWT_in_headers() throws Exception {
        String json = createCartRequestJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/secure/cart/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @InitSQL
    void removeFromCart_should_return_code_200_if_user_has_JWT_in_headers() throws Exception {
        String json = createCartRequestJson();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/secure/cart/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @InitSQL
    void getCartByUserId_should_return_code_200_if_user_has_JWT_in_headers() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/secure/cart/1")
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @InitSQL
    @Transactional
    void addToCart_should_adds_products_to_cart() throws Exception {//
        String json = createCartRequestJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/secure/cart/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Cart cart = entityManager.find(Cart.class, 1L);

        Assertions.assertFalse(cart.getPackagingList().isEmpty());
        Assertions.assertEquals(1, cart.getPackagingList().get(0).getId().getProductId());
        Assertions.assertEquals(200, cart.getPackagingList().get(0).getId().getAmount());

    }

    @Test
    @InitSQL
    @Transactional
    void removeFromCart_should_remove_item_from_cart_by_id() throws Exception {
        String json = createCartRequestJson();
        entityManager
                .createNativeQuery("INSERT INTO carts_packaging values (1,1,200),(1,2,500)")
                .executeUpdate();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/secure/cart/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(json));

        Cart cart = entityManager.find(Cart.class, 1L);

        Assertions.assertEquals(2, cart.getPackagingList().get(0).getId().getProductId());

    }
    private String createCartRequestJson(){
        return """
                {
                  "cartId": 1,
                  "productId": 1,
                  "amount": 200
                }
                """;
    }
}
