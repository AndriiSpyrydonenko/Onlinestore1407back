package com.svitsmachnogo.api.controller;

import com.svitsmachnogo.api.InitSQL;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import com.svitsmachnogo.api.domain.entity.User;
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

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class ProductControllerTest {

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
    void setup() {
        User user = entityManager.find(User.class, 2L);

        token = "Bearer " + jwtTokenUtils.generateToken(userService.convertToUserDetails(user));
    }

    @Test
    @InitSQL
    @DisplayName(value = "addProduct should return 401 code if Jwt no exist")
    void addProductReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName(value = "getSubcategoriesByCategoryId should return 401 code if Jwt no exist")
    void getSubcategoriesByCategoryIdReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/products/subcategories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName(value = "addProduct should return 403 code if Jwt has not an ADMIN role")
    void addProductReturn403() throws Exception {

        User user = entityManager.find(User.class, 1L);

        token = "Bearer " + jwtTokenUtils.generateToken(userService.convertToUserDetails(user));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @InitSQL
    @DisplayName(value = "getSubcategoriesByCategoryId should return 403 code if Jwt has not an ADMIN role")
    void getSubcategoriesByCategoryIdReturn403() throws Exception {

        User user = entityManager.find(User.class, 1L);

        token = "Bearer " + jwtTokenUtils.generateToken(userService.convertToUserDetails(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/products/subcategories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @InitSQL
    @DisplayName(value = "addProduct should return 201 code")
    void addProductReturn201() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(getJson()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @InitSQL
    @DisplayName(value = "getSubcategoriesByCategoryId should return 200 code ")
    void getSubcategoriesByCategoryIdReturn200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/products/subcategories?categoryId=nuts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName(value = "addProduct should adds product to DB")
    void addProductAddsProductToDB() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/products")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(getJson()));

        Product product = entityManager.find(Product.class, 11);

        assertNotNull(product);
        assertEquals("Супер пупер горіх", product.getName());
        assertEquals(1000000, product.getQuantity());
        assertEquals(11090099, product.getArticle());
        assertEquals(2, product.getPictures().size());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName(value = "addProduct should adds product and new subcategory to DB ")
    void addProductAddsNewSubcategoryToDB() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/products")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(getJson()));

        Product product = entityManager.find(Product.class, 11);
        Subcategory subcategory = entityManager.find(Subcategory.class, "jusic");

        assertNotNull(product);
        assertNotNull(subcategory);

        assertEquals("Супер пупер горіх", product.getName());
        assertTrue(subcategory.getProducts().contains(product));
    }

    private String getJson() {
        return """
                {
                  "categoryId": "nuts",
                  "article": 11090099,
                  "name": "Супер пупер горіх",
                  "description": "string",
                  "bigDescription": "string",
                  "nutritionalValue": "string",
                  "usage": "string",
                  "countryProducer": "Україна",
                  "discountPercent": 0,
                  "totalQuantity": 1000000,
                  "unit": "г",
                  "subcategories": [
                    {
                      "subcategoryId": "are_not_roast",
                      "subcategoryName": "Сирі",
                      "subcategoryTitle": "Тип обробки"
                    },{
                      "subcategoryId": "jusic",
                      "subcategoryName": "Джус",
                      "subcategoryTitle": "Приколь"
                    }
                  ],
                  "packagings": [
                    {
                      "productId": 0,
                      "amount": 202,
                      "cost": 100
                    }, {
                      "productId": 0,
                      "amount": 550,
                      "cost": 300
                    }, {
                      "productId": 0,
                      "amount": 1000,
                      "cost": 1111
                    }
                  ],
                  "pictures": [
                    "https://storage.googleapis.com/svsmach/kit.jpg",
                    "https://storage.googleapis.com/svsmach/kit.jpg"
                  ]
                }
                """;
    }
}