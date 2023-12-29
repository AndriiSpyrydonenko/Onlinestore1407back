package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {

    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:drop_test_data.sql", executionPhase = AFTER_TEST_METHOD),
            @Sql(value = "classpath:create_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    public void removePackagingById_should_remove_item_by_specify_id() {
        jdbcTemplate.update("""
                INSERT INTO carts_packaging VALUES (1,1,200), (1,2,500)
                """);

        cartRepository.removePackagingById(1L, 1, 200);

        Cart cart = entityManager.getEntityManager().find(Cart.class, 1L);

        assertEquals(1, cart.getPackagingList().size());
        assertEquals(2, cart.getPackagingList().get(0).getId().getProductId());
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:drop_test_data.sql", executionPhase = AFTER_TEST_METHOD),
            @Sql(value = "classpath:create_test_data.sql", executionPhase = BEFORE_TEST_METHOD)
    })
    public void removeAllPackagingById_should_remove_all_items_by_user_si() {
        jdbcTemplate.update("""
                INSERT INTO carts_packaging VALUES (1,1,200), (1,2,500)
                """);

        cartRepository.removeAllPackagingById(1L);

        Cart cart = entityManager.getEntityManager().find(Cart.class, 1L);

        assertTrue(cart.getPackagingList().isEmpty());
    }
}