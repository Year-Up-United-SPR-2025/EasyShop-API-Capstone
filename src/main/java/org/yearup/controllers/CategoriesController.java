package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@RestController
@RequestMapping("/categories")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category) {
        // Update the category by id
        categoryDao.update(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        // Check if the category exists
        if (!categoryDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Check for dependent products
        if (productDao.existsByCategoryId(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete category with associated products.");
        }

        // Delete the category
        categoryDao.delete(id);
        return ResponseEntity.noContent().build();
    }
}
