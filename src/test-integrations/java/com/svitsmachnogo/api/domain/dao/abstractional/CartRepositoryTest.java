package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Cart;
import com.svitsmachnogo.api.domain.entity.User;
import com.svitsmachnogo.api.domain.entity.UserProfile;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setup() {
        entityManager.clear();

        User user = new User();
        user.setEmail("email@gmail.com");
        user = entityManager.persistAndFlush(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setId(user.getId());
        userProfileRepository.saveAndFlush(userProfile);

        Cart cart = new Cart();
        cart.setUserProfile(userProfile);
        cart.setId(userProfile.getId());
        cartRepository.saveAndFlush(cart);
    }

    @AfterEach
    public void tearDown(){
        userRepository.deleteAll();
        userProfileRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    public void findByIdShouldReturnCart(){
        Cart cart = cartRepository
                .findAll()
                .stream()
                .findAny()
                .orElse(null);

        Assertions.assertNotNull(cart);
    }

    @Test
    public void findByIdShouldReturnCartWithSpecifyId(){

        Cart cart = cartRepository
                .findById(2L)
                .orElse(null);
        Assertions.assertEquals(2L, cart.getId());

    }
}