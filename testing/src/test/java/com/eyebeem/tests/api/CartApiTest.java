package com.eyebeem.tests.api;

import com.eyebeem.tests.BaseApiTest;
import com.eyebeem.tests.DataDependent;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;

/**
 * API tests for cart: get cart for users.
 */
@DataDependent
public class CartApiTest extends BaseApiTest {

    @Test
    public void getCart_user1_returns200() throws Exception {
        HttpResponse<String> res = api.get("/api/cart?userId=1");
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test
    public void getCart_returnsJsonObject() throws Exception {
        HttpResponse<String> res = api.get("/api/cart?userId=1");
        JsonObject c = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertTrue(c.has("items"));
    }

    @Test
    public void getCart_eachUserReturns200() throws Exception {
        for (long userId = 1; userId <= 5; userId++) {
            HttpResponse<String> res = api.get("/api/cart?userId=" + userId);
            Assert.assertEquals(res.statusCode(), 200, "Cart for user " + userId);
        }
    }

    @Test
    public void addToCart_thenGetCart_containsItem() throws Exception {
        api.delete("/api/cart?userId=1");
        HttpResponse<String> addRes = api.post("/api/cart/items", "{\"userId\":1,\"productId\":2,\"quantity\":1}");
        Assert.assertEquals(addRes.statusCode(), 200, "Add to cart failed - check product 2 has stock");
        HttpResponse<String> res = api.get("/api/cart?userId=1");
        JsonObject c = api.parseJson(res.body()).getAsJsonObject();
        var items = c.getAsJsonArray("items");
        boolean hasProduct2 = false;
        long cartItemId = -1;
        for (var i = 0; i < items.size(); i++) {
            var obj = items.get(i).getAsJsonObject();
            if (obj.get("productId").getAsInt() == 2) {
                hasProduct2 = true;
                cartItemId = obj.get("id").getAsLong();
                break;
            }
        }
        Assert.assertTrue(hasProduct2, "Cart should contain product 2 after add (product 1 may be out of stock)");
        if (cartItemId > 0) api.delete("/api/cart/items/" + cartItemId + "?userId=1");
    }
}
