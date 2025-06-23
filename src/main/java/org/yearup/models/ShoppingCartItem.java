package org.yearup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;

/**
 * Represents a single item in a shopping cart with product details,
 * quantity, and discount information.
 */
public class ShoppingCartItem {
    private Product product = null;       // The product being purchased
    private int quantity = 1;            // Quantity of this product in cart (default: 1)
    private BigDecimal discountPercent = BigDecimal.ZERO; // Discount percentage (default: 0%)

    /**
     * Gets the product associated with this cart item
     * @return The Product object
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product for this cart item
     * @param product The Product to associate with this item
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the quantity of this product in the cart
     * @return The quantity as an integer
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of this product in the cart
     * @param quantity The new quantity (must be positive)
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the discount percentage applied to this item
     * @return The discount percentage as BigDecimal
     */
    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Sets the discount percentage for this item
     * @param discountPercent The discount percentage (0-100)
     */
    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    /**
     * Gets the product ID (ignored in JSON serialization)
     * @return The product ID
     */
    @JsonIgnore
    public int getProductId() {
        return this.product.getProductId();
    }

    /**
     * Calculates the line total for this cart item (price × quantity minus discount)
     * @return The calculated line total as BigDecimal
     */
    public BigDecimal getLineTotal() {
        BigDecimal basePrice = product.getPrice();
        BigDecimal quantity = new BigDecimal(this.quantity);
        BigDecimal subTotal = basePrice.multiply(quantity);
        BigDecimal discountAmount = subTotal.multiply(discountPercent);
        return subTotal.subtract(discountAmount);
    }
}
