package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a shopping cart that holds items for purchase.
 * Each item is stored with its product ID as the key for quick lookup.
 * Provides methods to manage cart items and calculate the total cost.
 */
public class ShoppingCart {
    // Maps product IDs to their corresponding cart items
    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    /**
     * Gets all items in the shopping cart
     * @return Map of product IDs to ShoppingCartItem objects
     */
    public Map<Integer, ShoppingCartItem> getItems() {
        return items;
    }

    /**
     * Replaces all items in the cart with new items
     * @param items New map of items to set
     */
    public void setItems(Map<Integer, ShoppingCartItem> items) {
        this.items = items;
    }

    /**
     * Checks if a product exists in the cart
     * @param productId The ID of the product to check
     * @return true if the product is in the cart, false otherwise
     */
    public boolean contains(int productId) {
        return items.containsKey(productId);
    }

    /**
     * Adds a new item to the cart or updates quantity if already exists
     * @param item The shopping cart item to add/update
     */
    public void add(ShoppingCartItem item) {
        int productId = item.getProductId();
        if (contains(productId)) {
            // Update quantity if item exists
            ShoppingCartItem existingItem = get(productId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            // Add new item if it doesn't exist
            items.put(productId, item);
        }
    }

    /**
     * Removes an item from the cart
     * @param productId The ID of the product to remove
     */
    public void remove(int productId) {
        items.remove(productId);
    }

    /**
     * Updates the quantity of a specific product in the cart
     * @param productId The ID of the product to update
     * @param quantity The new quantity (must be positive)
     * @throws IllegalArgumentException if quantity is not positive
     */
    public void updateQuantity(int productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        if (contains(productId)) {
            get(productId).setQuantity(quantity);
        }
    }

    /**
     * Gets a specific item from the cart
     * @param productId The ID of the product to retrieve
     * @return The ShoppingCartItem or null if not found
     */
    public ShoppingCartItem get(int productId) {
        return items.get(productId);
    }

    /**
     * Calculates the total cost of all items in the cart
     * @return The grand total as BigDecimal
     */
    public BigDecimal getTotal() {
        return items.values().stream()
                .map(ShoppingCartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Clears all items from the shopping cart
     */
    public void clear() {
        items.clear();
    }

    /**
     * Gets the number of unique products in the cart
     * @return Count of distinct products
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Gets the total quantity of all items in the cart
     * @return Sum of all quantities
     */
    public int getTotalQuantity() {
        return items.values().stream()
                .mapToInt(ShoppingCartItem::getQuantity)
                .sum();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                ", total=" + getTotal() +
                '}';
    }
}
