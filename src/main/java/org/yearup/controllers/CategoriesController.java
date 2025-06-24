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
@RequestMapping("/categories")  // Maps this controller to the /categories endpoint
@CrossOrigin  // Allows cross-origin requests
public class CategoriesController {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Autowired  // Autowires the dependencies
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping  // Maps GET requests to this method
    public List<Category> getAll() {
        return categoryDao.getAllCategories();  // Returns all categories
    }

    @GetMapping("/{id}")  // Maps GET requests with an ID to this method
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);  // Returns the category by ID
    }

    @GetMapping("/{categoryId}/products")  // Maps GET requests for products by category ID
    public List<Product> getProductsById(@PathVariable int categoryId) {
        return productDao.getProductsByCategoryId(categoryId);  // Returns products by category ID
    }

    @PostMapping  // Maps POST requests to this method
    @PreAuthorize("hasRole('ADMIN')")  // Ensures only ADMIN can call this function
    public Category addCategory(@RequestBody Category category) {
        return categoryDao.create(category);  // Inserts the category
    }

    @PutMapping("/{id}")  // Maps PUT requests with an ID to this method
    @PreAuthorize("hasRole('ADMIN')")  // Ensures only ADMIN can call this function
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        categoryDao.update(id, category);  // Updates the category by ID
    }

    @DeleteMapping("/{id}")  // Maps DELETE requests with an ID to this method
    @PreAuthorize("hasRole('ADMIN')")  // Ensures only ADMIN can call this function
    public void deleteCategory(@PathVariable int id) {
        categoryDao.delete(id);  // Deletes the category by ID
    }
}
