package com.svitsmachnogo.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svitsmachnogo.api.InitSQL;
import com.svitsmachnogo.api.domain.dao.abstractional.OrderRepository;
import com.svitsmachnogo.api.domain.entity.Order;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.service.abstractional.UserService;
import com.svitsmachnogo.api.utils.JwtTokenUtils;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
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

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class OrderControllerTest {

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
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        User user = entityManager.find(User.class, 1L);

        token = "Bearer " + jwtTokenUtils.generateToken(userService.convertToUserDetails(user));
    }

    @Test
    @InitSQL
    @DisplayName(value = "getOrderById should return 401 code if Jwt no exist")
    void getOrderByIdReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/secure/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName(value = "getAllOrder should return 401 code if Jwt no exist")
    void getAllOrderReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/secure/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName(value = "getOrderByUserId should return 401 code if Jwt no exist")
    void getOrderByUserIdReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/secure/orders/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName(value = "createOrderByUserId should return 401 code if Jwt no exist")
    void createOrderByUserIdReturn401() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/secure/orders/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @InitSQL
    @DisplayName(value = "getAllOrder should return 403 code if JWT has not admin role")
    void getAllOrderReturn403WithoutAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName(value = "getOrderById should return 200 and order by specific id")
    void getOrderByIdReturn200() throws Exception {

        initOrder();

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/secure/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = mapper.readTree(response);

        Assertions.assertEquals(1, jsonNode.get("id").asInt());
        Assertions.assertEquals(481, jsonNode.get("totalCost").asInt());
        Assertions.assertEquals(1, jsonNode.get("userId").asInt());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName(value = "getAllOrder should return 200 and all orders ")
    void getAllOrderReturn200() throws Exception {

        initOrder();

        User user = entityManager.find(User.class, 2L);

        token = "Bearer " + jwtTokenUtils.generateToken(userService.convertToUserDetails(user));

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Order> orders = mapper
                .readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Order.class));

        Assertions.assertEquals(1, orders.get(0).getId());
        Assertions.assertEquals(481, orders.get(0).getTotalCost());
        Assertions.assertEquals("first", orders.get(0).getComment());
        Assertions.assertEquals(2, orders.get(1).getId());
        Assertions.assertEquals(481, orders.get(1).getTotalCost());
        Assertions.assertEquals("second", orders.get(1).getComment());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName(value = "getOrdersByUserId should return 200 and all orders by userId")
    void getOrdersByUserIdReturn200() throws Exception {

        initOrder();

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/secure/orders/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Order> orders = mapper
                .readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, Order.class));

        Assertions.assertEquals(1, orders.get(0).getId());
        Assertions.assertEquals(481, orders.get(0).getTotalCost());
        Assertions.assertEquals("first", orders.get(0).getComment());
        Assertions.assertEquals(2, orders.get(1).getId());
        Assertions.assertEquals(481, orders.get(1).getTotalCost());
        Assertions.assertEquals("second", orders.get(1).getComment());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName(value = "createOrderByUserId should adds orders by userId to DB and return 200 code")
    void createOrderByUserIdReturn200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/secure/orders/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(createJsonOrderDto()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Order order = entityManager.find(Order.class, 1L);

        Assertions.assertEquals(1, order.getId());
        Assertions.assertEquals(1693, order.getTotalCost());
        Assertions.assertEquals("comment", order.getComment());
        Assertions.assertEquals("Vanya", order.getCustomerName());
        Assertions.assertEquals(2, order.getPackagingList().get(0).getAmountOfUnits());
        Assertions.assertEquals(1, order.getPackagingList().get(1).getAmountOfUnits());
    }

    @Test
    @InitSQL
    @Transactional
    @DisplayName(value = "createOrderByNoUser should adds orders with no user to DB and return 200 code")
    void createOrderByNoUserReturn200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/orders/unauthorized-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonOrderDto()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Order order = entityManager.find(Order.class, 1L);

        Assertions.assertNull(order.getUserProfile());
        Assertions.assertEquals(1, order.getId());
        Assertions.assertEquals(1693, order.getTotalCost());
        Assertions.assertEquals("comment", order.getComment());
        Assertions.assertEquals("Vanya", order.getCustomerName());
        Assertions.assertEquals(2, order.getPackagingList().get(0).getAmountOfUnits());
        Assertions.assertEquals(1, order.getPackagingList().get(1).getAmountOfUnits());
    }

    private void initOrder() {
        entityManager.createNativeQuery("""
                INSERT INTO orders (user_id, order_comment, total_cost, customer_name, customer_surname, customer_phone_number, customer_address, create_date, pay_type)
                VALUES
                (1, 'first', 481, 'John', 'Doe', '+1234567890', '123 Main St, Cityville', now(), 'card'),
                (1, 'second', 481, 'John', 'Doe', '+1234567890', '123 Main St, Cityville', now(), 'cash');
                """).executeUpdate();

        entityManager.createNativeQuery("""
                INSERT INTO orders_packaging (order_id, count, product_id, amount)
                VALUES (1, 1, 1, 200),
                       (2, 1, 2, 500),
                       (1, 2, 1, 200),
                       (2, 2, 2, 500);
                """).executeUpdate();
    }

    private String createJsonOrderDto() {
        return """
                {
                  "userId": 1,
                  "comment": "comment",
                  "customerName": "Vanya",
                  "customerSurname": "Demydenko",
                  "customerPhoneNumber": "+380988760800",
                  "customerAddress": "Address",
                  "payType": "cart",
                  "packagingList": [
                    {
                      "count": 2, 
                      "productId": 3,
                      "amount": 200,
                      "cost": 768
                    },
                    {
                      "count": 1, 
                      "productId": 4,
                      "amount": 500,
                      "cost": 925
                    }
                  ]
                }
                """;
    }
}