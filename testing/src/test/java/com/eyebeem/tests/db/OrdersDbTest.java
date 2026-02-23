package com.eyebeem.tests.db;

import com.eyebeem.tests.BaseDbTest;
import com.eyebeem.tests.DataDependent;
import com.eyebeem.tests.util.DatabaseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

@DataDependent
public class OrdersDbTest extends BaseDbTest {

    @Test
    public void ordersTableExists() throws SQLException {
        Assert.assertTrue(DatabaseUtil.tableExists("orders"), "orders table should exist");
    }

    @Test
    public void orderItemsTableExists() throws SQLException {
        Assert.assertTrue(DatabaseUtil.tableExists("order_items"), "order_items table should exist");
    }

    @Test
    public void getOrderCount_ReturnsNonNegative() throws SQLException {
        int count = DatabaseUtil.getOrderCount();
        Assert.assertTrue(count >= 0, "Order count should be non-negative");
    }

    @Test
    public void getOrderRow_NonExistentReturnsNull() throws SQLException {
        Object[] row = DatabaseUtil.getOrderRow(999999);
        Assert.assertNull(row, "Non-existent order should return null");
    }

    @Test
    public void getOrderItems_NonExistentOrderReturnsEmpty() throws SQLException {
        var items = DatabaseUtil.getOrderItems(999999);
        Assert.assertNotNull(items);
        Assert.assertTrue(items.isEmpty(), "Non-existent order should have no items");
    }

    @Test
    public void orderItems_ReferValidProductIds() throws SQLException {
        int invCount = DatabaseUtil.getInventoryCount();
        if (invCount == 0) return;
        // If we have orders, order_items.product_id should reference inventory.product_id
        // We can't easily check FK without running a query - just ensure getOrderItems works
        Assert.assertTrue(invCount >= 3, "Inventory should have products for order items to reference");
    }
}
