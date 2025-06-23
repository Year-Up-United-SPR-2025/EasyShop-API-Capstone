package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")  // Allow cross-site origin requests
public class CategoriesController {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping
    public List<Category> getAll() {
        // Find and return all categories
        return categoryDao.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        // Get the category by id
        return categoryDao.getById(id);
    }

    @GetMapping("/{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId) {
        // Get a list of products by categoryId
        return productDao.listByCategoryId(categoryId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")  // Ensure that only an ADMIN can call this function
    public Category addCategory(@RequestBody Category category) {
        // Insert the category
        return categoryDao.create(category);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Ensure that only an ADMIN can call this function
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        // Update the category by id
        categoryDao.update(id, category);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Ensure that only an ADMIN can call this function
    public void deleteCategory(@PathVariable int id) {
        // Delete the category by id
        categoryDao.delete(id);
    }
}
