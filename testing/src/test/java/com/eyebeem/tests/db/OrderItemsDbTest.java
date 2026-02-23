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
 * DB tests for order_items (aligned with db test/orderitems.json).
 */
@DataDependent
public class OrderItemsDbTest extends BaseDbTest {

    @Test
    public void order1_hasOneItem_eCat() throws SQLException {
        List<Object[]> items = DatabaseUtil.getOrderItems(1);
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0)[1], 1);
        Assert.assertEquals(items.get(0)[2], "e-CAT");
        Assert.assertEquals(((Number) items.get(0)[3]).intValue(), 1);
        Assert.assertEquals(((BigDecimal) items.get(0)[4]).doubleValue(), 99.99, 0.01);
    }

    @Test
    public void order2_hasOneItem_eDog() throws SQLException {
        List<Object[]> items = DatabaseUtil.getOrderItems(2);
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0)[2], "e-DOG");
        Assert.assertEquals(((Number) items.get(0)[3]).intValue(), 1);
    }

    @Test
    public void order3_hasOneItem_4xECat() throws SQLException {
        List<Object[]> items = DatabaseUtil.getOrderItems(3);
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0)[2], "e-CAT");
        Assert.assertEquals(((Number) items.get(0)[3]).intValue(), 4);
    }

    @Test
    public void order4_hasOneItem_eHamster() throws SQLException {
        List<Object[]> items = DatabaseUtil.getOrderItems(4);
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0)[2], "e-HAMSTER");
        Assert.assertEquals(((Number) items.get(0)[3]).intValue(), 1);
    }

    @Test
    public void order1_unitPrice99_99() throws SQLException {
        List<Object[]> items = DatabaseUtil.getOrderItems(1);
        Assert.assertEquals(((BigDecimal) items.get(0)[4]).doubleValue(), 99.99, 0.01);
    }

    @Test
    public void orderItems_productIdsReferInventory() throws SQLException {
        for (long orderId = 1; orderId <= 4; orderId++) {
            List<Object[]> items = DatabaseUtil.getOrderItems(orderId);
            for (Object[] item : items) {
                int productId = ((Number) item[1]).intValue();
                Assert.assertTrue(productId >= 1 && productId <= 3);
            }
        }
    }
}
