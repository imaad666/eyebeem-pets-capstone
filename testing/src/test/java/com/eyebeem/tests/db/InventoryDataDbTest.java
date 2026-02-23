package com.eyebeem.tests.db;

import com.eyebeem.tests.BaseDbTest;
import com.eyebeem.tests.DataDependent;
import com.eyebeem.tests.util.DatabaseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

/**
 * DB tests for inventory (aligned with db test/inventory.json).
 * Inventory: product 1 qty 5, product 2 qty 9, product 3 qty 9.
 */
@DataDependent
public class InventoryDataDbTest extends BaseDbTest {

    @Test
    public void product1_hasNonNegativeQuantity() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(1);
        Assert.assertNotNull(row);
        Assert.assertTrue(((Number) row[4]).intValue() >= 0, "e-CAT quantity should be >= 0 (stock changes with orders)");
    }

    @Test
    public void product2_hasNonNegativeQuantity() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(2);
        Assert.assertNotNull(row);
        Assert.assertTrue(((Number) row[4]).intValue() >= 0);
    }

    @Test
    public void product3_hasNonNegativeQuantity() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(3);
        Assert.assertNotNull(row);
        Assert.assertTrue(((Number) row[4]).intValue() >= 0);
    }

    @Test
    public void product1_nameECat() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(1);
        Assert.assertEquals(row[1], "e-CAT");
    }

    @Test
    public void product2_nameEDog() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(2);
        Assert.assertEquals(row[1], "e-DOG");
    }

    @Test
    public void product3_nameEHamster() throws SQLException {
        Object[] row = DatabaseUtil.getInventoryRow(3);
        Assert.assertEquals(row[1], "e-HAMSTER");
    }
}
