package org.yearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

/**
 * REST controller for handling shopping cart operations.
 * This controller provides endpoints to manage the shopping cart for authenticated users.
 */
@RestController
@RequestMapping("/shopping_cart") // Base URL for shopping cart operations
public class ShoppingCartController {
    private final ShoppingCartDao shoppingCartDao; // DAO for shopping cart operations

    /**
     * Constructor with dependency injection.
     * @param shoppingCartDao The shopping cart data access object.
     */
    public ShoppingCartController(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    /**
     * Gets the current user's shopping cart.
     * @param user The authenticated user (automatically injected).
     * @return The user's shopping cart.
     */
    @GetMapping
    public ResponseEntity<ShoppingCart> getCart(@AuthenticationPrincipal User user) {
        ShoppingCart cart = shoppingCartDao.getCartByUserId(user.getId());
        return ResponseEntity.ok(cart); // Return the cart with 200 OK status
    }

    /**
     * Adds a product to the user's cart.
     * @param user The authenticated user.
     * @param productId The ID of the product to add.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/products/{productId}")
    public ResponseEntity<Void> addItem(@AuthenticationPrincipal User user,
                                        @PathVariable int productId) {
        shoppingCartDao.addToCart(user.getId(), productId);
        return ResponseEntity.status(201).build(); // Return 201 Created status
    }

    /**
     * Updates the quantity of a product in the user's cart.
     * @param user The authenticated user.
     * @param productId The ID of the product to update.
     * @param quantity The new quantity to set.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<Void> updateQuantity(@AuthenticationPrincipal User user,
                                               @PathVariable int productId,
                                               @RequestBody int quantity) {
        shoppingCartDao.updateQuantity(user.getId(), productId, quantity);
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }

    /**
     * Clears all items from the user's cart.
     * @param user The authenticated user.
     * @return ResponseEntity indicating the result of the operation.
     */
    @DeleteMapping
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal User user) {
        shoppingCartDao.clearCart(user.getId());
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }
}
