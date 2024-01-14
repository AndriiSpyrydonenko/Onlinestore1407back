package com.svitsmachnogo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svitsmachnogo.api.InitSQL;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.domain.entity.UserProfile;
import com.svitsmachnogo.api.dto.product.SimpleProductDto;
import com.svitsmachnogo.api.service.abstractional.UserService;
import com.svitsmachnogo.api.utils.JwtTokenUtils;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private ObjectMapper mapper;

    private String token;

    @BeforeEach
    void setup() {
        User user = entityManager.find(User.class, 1L);

        token = "Bearer " + jwtTokenUtils.generateToken(userService.convertToUserDetails(user));
    }

    @Test
    @InitSQL
    @DisplayName("addToWishlist should return 401 code if request has not a jwt in the headers")
    void addToWishlistReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/secure/wishlist/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName("deleteFromWishlist should return 401 code if request has not a jwt in the headers")
    void deleteFromWishlistReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/secure/wishlist/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName("getWishlist should return 401 code if request has not a jwt in the headers")
    void getWishlistReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/secure/wishlist/user/1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName("addToWishlist should return 201 code and add product to wishlist")
    void addToWishlistReturn201() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/secure/wishlist/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(createJson()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        UserProfile userProfile = entityManager.find(UserProfile.class, 1L);

        assertEquals(1, userProfile.getWishList().get(0).getId()); // added correct product
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName("deleteFromWishlist should return 200 code and delete product from wishlist")
    void deleteFromWishlistReturn200() throws Exception {

        entityManager.createNativeQuery("""
                        INSERT INTO wishlists 
                        values (1, 1);
                        """)
                .executeUpdate();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/secure/wishlist/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(createJson()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        UserProfile userProfile = entityManager.find(UserProfile.class, 1L);

        assertTrue(userProfile.getWishList().isEmpty());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName("getWishlist should return 200 code and product list that containing in the wishlist")
    void getWishlistReturn200() throws Exception {
        entityManager.createNativeQuery("""
                        INSERT INTO wishlists 
                        values (1, 1),
                               (1, 2);
                        """)
                .executeUpdate();

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/secure/wishlist/user/1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<SimpleProductDto> products = mapper
                .readValue(
                        response,
                        mapper.getTypeFactory().constructCollectionType(List.class, SimpleProductDto.class)
                );

        assertEquals(1, products.get(0).getId());
        assertEquals(2, products.get(1).getId());
    }

    private String createJson() {
        return """
                {
                  "userId": 1,
                  "productId": 1
                }
                """;
    }
}