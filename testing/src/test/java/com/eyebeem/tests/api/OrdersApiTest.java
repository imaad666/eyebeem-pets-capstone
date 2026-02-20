package com.eyebeem.tests.api;

import com.eyebeem.tests.BaseApiTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;

/**
 * API tests for order history: per-user orders and order contents.
 */
public class OrdersApiTest extends BaseApiTest {

    @Test
    public void orderHistory_user1_hasOneOrder() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=1");
        Assert.assertEquals(res.statusCode(), 200);
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1, "User 1 (Test User) should have 1 order");
    }

    @Test
    public void orderHistory_user2_empty() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=2");
        Assert.assertEquals(res.statusCode(), 200);
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 0, "User 2 (Jane Doe) should have 0 orders");
    }

    @Test
    public void orderHistory_user3_hasOneOrder() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=3");
        Assert.assertEquals(res.statusCode(), 200);
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1);
    }

    @Test
    public void orderHistory_user4_hasOneOrder() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=4");
        Assert.assertEquals(res.statusCode(), 200);
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1);
    }

    @Test
    public void orderHistory_user5_hasOneOrder() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=5");
        Assert.assertEquals(res.statusCode(), 200);
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1);
    }

    @Test
    public void orderHistory_user1_orderTotal99_99() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=1");
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1);
        double total = arr.get(0).getAsJsonObject().get("total").getAsDouble();
        Assert.assertEquals(total, 99.99, 0.01);
    }

    @Test
    public void orderHistory_user3_orderTotal129_99() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=3");
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1);
        Assert.assertEquals(arr.get(0).getAsJsonObject().get("total").getAsDouble(), 129.99, 0.01);
    }

    @Test
    public void orderHistory_user4_orderTotal399_96() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=4");
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1);
        Assert.assertEquals(arr.get(0).getAsJsonObject().get("total").getAsDouble(), 399.96, 0.01);
    }

    @Test
    public void orderHistory_user5_orderTotal49_99() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=5");
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 1);
        Assert.assertEquals(arr.get(0).getAsJsonObject().get("total").getAsDouble(), 49.99, 0.01);
    }

    @Test
    public void orderHistory_user1_orderHasItems() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=1");
        JsonObject order = api.parseJsonArray(res.body()).get(0).getAsJsonObject();
        Assert.assertTrue(order.has("items"));
        JsonArray items = order.getAsJsonArray("items");
        Assert.assertEquals(items.size(), 1);
        JsonObject item = items.get(0).getAsJsonObject();
        Assert.assertEquals(item.get("productName").getAsString(), "e-CAT");
        Assert.assertEquals(item.get("quantity").getAsInt(), 1);
        Assert.assertEquals(item.get("unitPrice").getAsDouble(), 99.99, 0.01);
    }

    @Test
    public void orderHistory_user3_orderHasEDog() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=3");
        JsonObject order = api.parseJsonArray(res.body()).get(0).getAsJsonObject();
        JsonArray items = order.getAsJsonArray("items");
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0).getAsJsonObject().get("productName").getAsString(), "e-DOG");
        Assert.assertEquals(items.get(0).getAsJsonObject().get("quantity").getAsInt(), 1);
    }

    @Test
    public void orderHistory_user4_orderHas4xECat() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=4");
        JsonObject order = api.parseJsonArray(res.body()).get(0).getAsJsonObject();
        JsonArray items = order.getAsJsonArray("items");
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0).getAsJsonObject().get("productName").getAsString(), "e-CAT");
        Assert.assertEquals(items.get(0).getAsJsonObject().get("quantity").getAsInt(), 4);
    }

    @Test
    public void orderHistory_user5_orderHasEHamster() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=5");
        JsonObject order = api.parseJsonArray(res.body()).get(0).getAsJsonObject();
        JsonArray items = order.getAsJsonArray("items");
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0).getAsJsonObject().get("productName").getAsString(), "e-HAMSTER");
        Assert.assertEquals(items.get(0).getAsJsonObject().get("quantity").getAsInt(), 1);
    }

    @Test
    public void orderHistory_returnsArray() throws Exception {
        HttpResponse<String> res = api.get("/api/orders/history?userId=1");
        Assert.assertTrue(res.body().trim().startsWith("["));
    }

    @Test
    public void orderHistory_eachOrderHasId() throws Exception {
        for (long userId = 1; userId <= 5; userId++) {
            HttpResponse<String> res = api.get("/api/orders/history?userId=" + userId);
            JsonArray arr = api.parseJsonArray(res.body());
            for (JsonElement e : arr) {
                Assert.assertTrue(e.getAsJsonObject().has("id"));
            }
        }
    }

    @Test
    public void orderHistory_eachOrderHasTotal() throws Exception {
        for (long userId = 1; userId <= 5; userId++) {
            HttpResponse<String> res = api.get("/api/orders/history?userId=" + userId);
            JsonArray arr = api.parseJsonArray(res.body());
            for (JsonElement e : arr) {
                Assert.assertTrue(e.getAsJsonObject().has("total"));
            }
        }
    }

    @Test
    public void orderHistory_eachOrderHasItems() throws Exception {
        for (long userId = 1; userId <= 5; userId++) {
            HttpResponse<String> res = api.get("/api/orders/history?userId=" + userId);
            JsonArray arr = api.parseJsonArray(res.body());
            for (JsonElement e : arr) {
                Assert.assertTrue(e.getAsJsonObject().has("items"));
            }
        }
    }
}
