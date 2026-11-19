
package com.example.rediscaching;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseService {

    private static final String URL =
            "jdbc:postgresql://localhost:5434/shop";

    private static final String USER = "app";
    private static final String PASSWORD = "apppass";

    public Product getProduct(int id) {

        String sql = "SELECT * FROM product WHERE id = ?";

        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {
                    return new Product(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getBigDecimal("price")
                    );
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
    public void updatePrice(int id, java.math.BigDecimal price) {

        String sql = "UPDATE product SET price = ? WHERE id = ?";

        try (Connection connection =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBigDecimal(1, price);
            statement.setInt(2, id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
