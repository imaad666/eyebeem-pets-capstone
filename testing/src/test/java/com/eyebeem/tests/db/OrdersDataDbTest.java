package com.eyebeem.tests.db;

import com.eyebeem.tests.BaseDbTest;
import com.eyebeem.tests.DataDependent;
import com.eyebeem.tests.util.DatabaseUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * DB tests for orders data (aligned with db test/orders.json).
 */
@DataDependent
public class OrdersDataDbTest extends BaseDbTest {

    @Test
    public void orderCount_atLeast4() throws SQLException {
        Assert.assertTrue(DatabaseUtil.getOrderCount() >= 4, "Expect at least 4 orders (from db test snapshot)");
    }

    @Test
    public void order1_user1_total99_99() throws SQLException {
        Object[] row = DatabaseUtil.getOrderRow(1);
        Assert.assertNotNull(row);
        Assert.assertEquals(((Number) row[1]).longValue(), 1L);
        Assert.assertEquals(((BigDecimal) row[2]).doubleValue(), 99.99, 0.01);
    }

    @Test
    public void order2_user3_total129_99() throws SQLException {
        Object[] row = DatabaseUtil.getOrderRow(2);
        Assert.assertNotNull(row);
        Assert.assertEquals(((Number) row[1]).longValue(), 3L);
        Assert.assertEquals(((BigDecimal) row[2]).doubleValue(), 129.99, 0.01);
    }

    @Test
    public void order3_user4_total399_96() throws SQLException {
        Object[] row = DatabaseUtil.getOrderRow(3);
        Assert.assertNotNull(row);
        Assert.assertEquals(((Number) row[1]).longValue(), 4L);
        Assert.assertEquals(((BigDecimal) row[2]).doubleValue(), 399.96, 0.01);
    }

    @Test
    public void order4_user5_total49_99() throws SQLException {
        Object[] row = DatabaseUtil.getOrderRow(4);
        Assert.assertNotNull(row);
        Assert.assertEquals(((Number) row[1]).longValue(), 5L);
        Assert.assertEquals(((BigDecimal) row[2]).doubleValue(), 49.99, 0.01);
    }

    @Test
    public void user1_hasOneOrder() throws SQLException {
        List<Object[]> orders = DatabaseUtil.getOrdersByUserId(1);
        Assert.assertEquals(orders.size(), 1);
    }

    @Test
    public void user2_hasNoOrders() throws SQLException {
        List<Object[]> orders = DatabaseUtil.getOrdersByUserId(2);
        Assert.assertEquals(orders.size(), 0);
    }

    @Test
    public void user3_hasOneOrder() throws SQLException {
        List<Object[]> orders = DatabaseUtil.getOrdersByUserId(3);
        Assert.assertEquals(orders.size(), 1);
    }

    @Test
    public void user4_hasOneOrder() throws SQLException {
        List<Object[]> orders = DatabaseUtil.getOrdersByUserId(4);
        Assert.assertEquals(orders.size(), 1);
    }

    @Test
    public void user5_hasOneOrder() throws SQLException {
        List<Object[]> orders = DatabaseUtil.getOrdersByUserId(5);
        Assert.assertEquals(orders.size(), 1);
    }
}
