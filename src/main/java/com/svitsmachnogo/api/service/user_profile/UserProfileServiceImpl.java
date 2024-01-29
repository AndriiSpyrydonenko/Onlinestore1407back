package com.svitsmachnogo.api.service.user_profile;

import com.svitsmachnogo.api.domain.dao.abstractional.UserProfileRepository;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.UserProfile;
import com.svitsmachnogo.api.exceptions.NoSuchUserException;
import com.svitsmachnogo.api.service.product.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of UserProfileService defining operations related to user profiles.
 * This service provides functionality to find user profiles, update profiles, manage wishlists,
 * and retrieve wishlist items for users.
 *
 * @author Vanya Demydenko
 */
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final ProductService productService;

    /**
     * Retrieves a user profile by its unique identifier.
     *
     * @param id The unique identifier of the user profile.
     * @return The UserProfile entity associated with the provided ID.
     * @throws NoSuchUserException If the user with the given ID does not exist.
     */
    @Override
    public UserProfile findById(Long id) {
        return userProfileRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchUserException(String.format("User with id: %s does not exist", id)));
    }

    /**
     * Updates the provided user profile details.
     *
     * @param userProfile The UserProfile entity with updated details.
     */
    @Override
    public void update(UserProfile userProfile) {
        userProfileRepository.saveAndFlush(userProfile);
    }

    /**
     * Adds a product to the wishlist of a specific user identified by user ID.
     *
     * @param userId    The ID of the user for whom the product is added to the wishlist.
     * @param productId The ID of the product to be added to the wishlist.
     */
    @Override
    @Transactional
    public void addToWishList(Long userId, Integer productId) {
        UserProfile user = findById(userId);
        Product product = productService.getProductById(productId);
        user.getWishList().add(product);
        userProfileRepository.saveAndFlush(user);
    }

    /**
     * Removes a product from the wishlist of a specific user identified by user ID.
     *
     * @param userId    The ID of the user for whom the product is removed from the wishlist.
     * @param productId The ID of the product to be removed from the wishlist.
     */
    @Override
    @Transactional
    public void removeFromWishList(Long userId, Integer productId) {
        UserProfile user = findById(userId);
        Product product = productService.getProductById(productId);
        user.getWishList().remove(product);
        userProfileRepository.saveAndFlush(user);
    }

    /**
     * Retrieves the wishlist items for a user identified by user ID.
     *
     * @param userId The ID of the user whose wishlist items are to be retrieved.
     * @return A list of Product entities representing the wishlist items of the user.
     */
    @Override
    @Transactional
    public List<Product> getWishListByUserId(Long userId) {
        return findById(userId).getWishList();
    }
}
