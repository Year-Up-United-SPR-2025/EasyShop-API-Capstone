package org.yearup.data;

import org.springframework.stereotype.Repository;
import org.yearup.models.ShoppingCart;

/**
 * Defines database operations for shopping cart functionality.
 */
@Repository
public interface ShoppingCartDao {
    /**
     * Retrieves the shopping cart for a specific user.
     * @param userId The ID of the user.
     * @return The user's shopping cart.
     */
    ShoppingCart getCartByUserId(int userId);

    /**
     * Adds a product to the user's cart.
     * @param userId The ID of the user.
     * @param productId The ID of the product to add.
     */
    void addToCart(int userId, int productId);

    /**
     * Updates the quantity of a product in the user's cart.
     * @param userId The ID of the user.
     * @param productId The ID of the product to update.
     * @param quantity The new quantity.
     */
    void updateQuantity(int userId, int productId, int quantity);

    /**
     * Clears all items from the user's cart.
     * @param userId The ID of the user.
     */
    void clearCart(int userId);
}
