package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Product;
import org.yearup.data.ProductDao;

import java.math.BigDecimal;
import java.util.List;

@RestController            // Marks this class as a Spring REST Controller
@RequestMapping("products") // Base URL path for all endpoints in this controller
@CrossOrigin               // Allows cross-origin requests from different domains
public class ProductsController
{
    private final ProductDao productDao;

    // Constructor with dependency injection
    @Autowired
    public ProductsController(ProductDao productDao)
    {
        this.productDao = productDao;
    }

    /**
     * Search for products with optional filters
     * @param categoryId Filter by category ID (optional)
     * @param minPrice Minimum price filter (optional)
     * @param maxPrice Maximum price filter (optional)
     * @param color Color filter (optional)
     * @return List of products matching search criteria
     */
    @GetMapping("")
    @PreAuthorize("permitAll()")  // Allows access to everyone
    public List<Product> search(
            @RequestParam(name="cat", required = false) Integer categoryId,
            @RequestParam(name="minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name="maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(name="color", required = false) String color)
    {
        try
        {
            // Delegate search operation to DAO layer
            return productDao.search(categoryId, minPrice, maxPrice, color);
        }
        catch(Exception ex)
        {
            // Return 500 error if something goes wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    /**
     * Get a single product by ID
     * @param id The ID of the product to retrieve
     * @return The requested product
     */
    @GetMapping("{id}")
    @PreAuthorize("permitAll()")  // Allows access to everyone
    public Product getById(@PathVariable int id)
    {
        try
        {
            var product = productDao.getById(id);

            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return product;
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    /**
     * Add a new product (Admin only)
     * @param product The product to add (from request body)
     * @return The newly created product
     */
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Restricts to ADMIN users only
    public Product addProduct(@RequestBody Product product)
    {
        try
        {
            // Delegate creation to DAO layer
            return productDao.create(product);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    /**
     * Update an existing product (Admin only)
     * @param id The ID of the product to update
     * @param product The updated product information (from request body)
     *
     * BUG FIX: Originally called create() which would make duplicates
     * Fixed to call update() instead
     */
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Restricts to ADMIN users only
    public void updateProduct(@PathVariable int id, @RequestBody Product product)
    {
        try
        {
            // NOTE: Fixed from productDao.create() to update() to prevent duplicates
            productDao.update(id, product);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    /**
     * Delete a product (Admin only)
     * @param id The ID of the product to delete
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")  // Restricts to ADMIN users only
    public void deleteProduct(@PathVariable int id)
    {
        try
        {
            // First verify the product exists
            var product = productDao.getById(id);

            if(product == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            // Delete the product
            productDao.delete(id);
        }
        catch(Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}
