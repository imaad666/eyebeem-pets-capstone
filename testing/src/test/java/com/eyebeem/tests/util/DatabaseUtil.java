package com.eyebeem.tests.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for database operations using JDBC.
 * Connects to the MySQL Docker container.
 */
public class DatabaseUtil {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/eyebeem?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DB_USER = "app";
    private static final String DB_PASSWORD = "app";
    
    /**
     * Get a database connection.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    /**
     * Execute a SELECT query and return ResultSet (caller must close).
     */
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
    
    /**
     * Execute INSERT/UPDATE/DELETE and return rows affected.
     */
    public static int executeUpdate(String sql) throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);
        }
    }
    
    /**
     * Clear all orders and order_items (useful for test cleanup).
     */
    public static void clearOrders() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM order_items");
            stmt.executeUpdate("DELETE FROM orders");
        }
    }
    
    /**
     * Clear all users (useful for test cleanup).
     */
    public static void clearUsers() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            stmt.executeUpdate("DELETE FROM users");
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
        }
    }
    
    /**
     * Get count of orders in database.
     */
    public static int getOrderCount() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM orders")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }
    
    /**
     * Get count of users in database.
     */
    public static int getUserCount() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }

    /** Check if a table exists in the database. */
    public static boolean tableExists(String tableName) throws SQLException {
        try (Connection conn = getConnection();
             ResultSet rs = conn.getMetaData().getTables(null, null, tableName, new String[]{"TABLE"})) {
            return rs.next();
        }
    }

    /** Get order row by id: [id, user_id, total, placed_at]. Returns null if not found. */
    public static Object[] getOrderRow(long orderId) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, user_id, total, placed_at FROM orders WHERE id = ?")) {
            ps.setLong(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new Object[]{
                    rs.getLong("id"),
                    rs.getObject("user_id") != null ? rs.getLong("user_id") : null,
                    rs.getBigDecimal("total"),
                    rs.getTimestamp("placed_at")
                };
            }
        }
    }

    /** Get order items for order: list of [order_id, product_id, product_name, quantity, unit_price]. */
    public static List<Object[]> getOrderItems(long orderId) throws SQLException {
        List<Object[]> out = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT order_id, product_id, product_name, quantity, unit_price FROM order_items WHERE order_id = ? ORDER BY product_id")) {
            ps.setLong(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Object[]{
                        rs.getLong("order_id"),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("unit_price")
                    });
                }
            }
        }
        return out;
    }

    /** Get inventory product count. */
    public static int getInventoryCount() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM inventory")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /** Get inventory row by product_id: [product_id, name, price, type, quantity]. Returns null if not found. */
    public static Object[] getInventoryRow(int productId) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT product_id, name, price, type, quantity FROM inventory WHERE product_id = ?")) {
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new Object[]{
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getString("type"),
                    rs.getInt("quantity")
                };
            }
        }
    }

    /** Get all inventory rows ordered by product_id. Each row: [product_id, name, price, type, quantity]. */
    public static List<Object[]> getAllInventory() throws SQLException {
        List<Object[]> out = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT product_id, name, price, type, quantity FROM inventory ORDER BY product_id")) {
            while (rs.next()) {
                out.add(new Object[]{
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getBigDecimal("price"),
                    rs.getString("type"),
                    rs.getInt("quantity")
                });
            }
        }
        return out;
    }

    /** Get orders for a user. Each row: [id, user_id, total, placed_at]. */
    public static List<Object[]> getOrdersByUserId(long userId) throws SQLException {
        List<Object[]> out = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT id, user_id, total, placed_at FROM orders WHERE user_id = ? ORDER BY id")) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Object[]{
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getBigDecimal("total"),
                        rs.getTimestamp("placed_at")
                    });
                }
            }
        }
        return out;
    }

    /** Get user row by id: [id, name, email, created_at]. Returns null if not found. */
    public static Object[] getUserRow(long userId) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, name, email, created_at FROM users WHERE id = ?")) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new Object[]{
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getTimestamp("created_at")
                };
            }
        }
    }
}
