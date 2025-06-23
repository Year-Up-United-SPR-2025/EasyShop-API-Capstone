package org.yearup.data.mysql;

import org.yearup.data.ProductDao;
import org.yearup.models.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySqlProductDao extends MySqlDaoBase implements ProductDao {

    public MySqlProductDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String color) {
        // Implement search logic
        return new ArrayList<>(); // Placeholder
    }

    @Override
    public List<Product> listByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return products;
    }

    @Override
    public Product getById(int productId) {
        // Implement logic to get a product by ID
        return null; // Placeholder
    }

    @Override
    public Product create(Product product) {
        // Implement logic to create a new product
        return null; // Placeholder
    }

    @Override
    public void update(int productId, Product product) {
        // Implement logic to update a product
    }

    @Override
    public void delete(int productId) {
        // Implement logic to delete a product
    }

    @Override
    public boolean existsByCategoryId(int categoryId) {
        String sql = "SELECT COUNT(*) > 0 FROM products WHERE category_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean(1); // Returns true if count > 0
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return false; // Return false if an exception occurs or no products found
    }

    private Product mapRow(ResultSet row) throws SQLException {
        int productId = row.getInt("product_id");
        String name = row.getString("name");
        BigDecimal price = row.getBigDecimal("price");
        int categoryId = row.getInt("category_id");
        String description = row.getString("description");
        String color = row.getString("color");
        int stock = row.getInt("stock");
        boolean isFeatured = row.getBoolean("featured");
        String imageUrl = row.getString("image_url");

        return new Product(productId, name, price, categoryId, description, color, stock, isFeatured, imageUrl);
    }
}
