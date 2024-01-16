package com.svitsmachnogo.api.service.user_profile;

import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.UserProfile;

import java.util.List;

/**
 * Service providing functionality related to user profiles.
 * It defines methods to find user profiles, update profiles, manage wishlists, and retrieve wishlist items for users.
 */
public interface UserProfileService {

    /**
     * Retrieves a user profile by its unique identifier.
     *
     * @param id The unique identifier of the user profile.
     * @return The UserProfile entity associated with the provided ID.
     */
    UserProfile findById(Long id);

    /**
     * Updates the provided user profile details.
     *
     * @param userProfile The UserProfile entity with updated details.
     */
    void update(UserProfile userProfile);

    /**
     * Adds a product to the wishlist of a specific user identified by user ID.
     *
     * @param userId    The ID of the user for whom the product is added to the wishlist.
     * @param productId The ID of the product to be added to the wishlist.
     */
    void addToWishList(Long userId, Integer productId);

    /**
     * Removes a product from the wishlist of a specific user identified by user ID.
     *
     * @param userId    The ID of the user for whom the product is removed from the wishlist.
     * @param productId The ID of the product to be removed from the wishlist.
     */
    void removeFromWishList(Long userId, Integer productId);

    /**
     * Retrieves the wishlist items for a user identified by user ID.
     *
     * @param userId The ID of the user whose wishlist items are to be retrieved.
     * @return A list of Product entities representing the wishlist items of the user.
     */
    List<Product> getWishListByUserId(Long userId);
}

