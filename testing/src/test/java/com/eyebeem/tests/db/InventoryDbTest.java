package com.eyebeem.tests.db;

import com.eyebeem.tests.BaseDbTest;
import com.eyebeem.tests.DataDependent;
import com.eyebeem.tests.util.DatabaseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@DataDependent
public class InventoryDbTest extends BaseDbTest {

    @Test
    public void inventoryTableExists() throws SQLException {
        Assert.assertTrue(DatabaseUtil.tableExists("inventory"), "inventory table should exist");
    }

    @Test
    public void inventoryHasAtLeastThreeProducts() throws SQLException {
        int count = DatabaseUtil.getInventoryCount();
        Assert.assertTrue(count >= 3, "Inventory should have at least 3 products, got " + count);
    }

    @Test
    public void product1_ECatExists() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(1);
        Assert.assertNotNull(row, "Product 1 (e-CAT) should exist");
        Assert.assertEquals(row[1], "e-CAT", "Product 1 name should be e-CAT");
    }

    @Test
    public void product2_EDogExists() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(2);
        Assert.assertNotNull(row, "Product 2 (e-DOG) should exist");
        Assert.assertEquals(row[1], "e-DOG", "Product 2 name should be e-DOG");
    }

    @Test
    public void product3_EHamsterExists() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(3);
        Assert.assertNotNull(row, "Product 3 (e-HAMSTER) should exist");
        Assert.assertEquals(row[1], "e-HAMSTER", "Product 3 name should be e-HAMSTER");
    }

    @Test
    public void product1_HasCorrectPrice() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(1);
        Assert.assertNotNull(row);
        Assert.assertEquals(((BigDecimal) row[2]).doubleValue(), 99.99, 0.01, "e-CAT price should be 99.99");
    }

    @Test
    public void product2_HasCorrectPrice() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(2);
        Assert.assertNotNull(row);
        Assert.assertEquals(((BigDecimal) row[2]).doubleValue(), 129.99, 0.01, "e-DOG price should be 129.99");
    }

    @Test
    public void product3_HasCorrectPrice() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(3);
        Assert.assertNotNull(row);
        Assert.assertEquals(((BigDecimal) row[2]).doubleValue(), 49.99, 0.01, "e-HAMSTER price should be 49.99");
    }

    @Test
    public void product1_HasQuantity() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(1);
        Assert.assertNotNull(row);
        Assert.assertTrue((Integer) row[4] >= 0, "e-CAT quantity should be >= 0");
    }

    @Test
    public void getAllInventory_ReturnsOrderedByProductId() throws SQLException {
        List<Object[]> rows = DatabaseUtil.getAllInventory();
        Assert.assertNotNull(rows);
        Assert.assertTrue(rows.size() >= 3);
        for (int i = 1; i < rows.size(); i++) {
            int prevId = (Integer) rows.get(i - 1)[0];
            int currId = (Integer) rows.get(i)[0];
            Assert.assertTrue(currId >= prevId, "Inventory should be ordered by product_id");
        }
    }
}
