package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@RestController  // Makes this class a REST controller
@RequestMapping("/categories")  // Maps this controller to the /categories URL
@CrossOrigin  // Allows cross-origin requests
public class ShoppingCartController
{
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Autowired  // Injects the CategoryDao and ProductDao
    public ShoppingCartController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping  // Handles GET requests for all categories
    public List<Category> getAll() {
        return categoryDao.getAllCategories();  // Returns all categories
    }

    @GetMapping("/{id}")  // Handles GET requests for a specific category by ID
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);  // Returns the category by ID
    }

    @GetMapping("/{categoryId}/products")  // Handles GET requests for products in a specific category
    public List<Product> getProductsById(@PathVariable int categoryId) {
        return productDao.listByCategoryId(categoryId);  // Returns products by category ID
    }

    @PostMapping  // Handles POST requests to add a new category
    @PreAuthorize("hasRole('ADMIN')")  // Ensures only ADMIN can call this function
    public Category addCategory(@RequestBody Category category) {
        return categoryDao.create(category);  // Inserts the category
    }

    @PutMapping("/{id}")  // Handles PUT requests to update a category
    @PreAuthorize("hasRole('ADMIN')")  // Ensures only ADMIN can call this function
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryDao.update(id, category);  // Updates the category by ID
    }

    @DeleteMapping("/{id}")  // Handles DELETE requests to remove a category
    @PreAuthorize("hasRole('ADMIN')")  // Ensures only ADMIN can call this function
    public void deleteCategory(@PathVariable int id) {
        categoryDao.delete(id);  // Deletes the category by ID
    }
}
