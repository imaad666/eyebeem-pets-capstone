package com.eyebeem.tests.api;

import com.eyebeem.tests.BaseApiTest;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;

/**
 * API tests for GET /api/users/{userId}.
 */
public class UsersApiTest extends BaseApiTest {

    @Test
    public void getUser1_returnsTestUser() throws Exception {
        HttpResponse<String> res = api.get("/api/users/1");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(u.get("id").getAsLong(), 1);
        Assert.assertEquals(u.get("name").getAsString(), "Test User");
        Assert.assertEquals(u.get("email").getAsString(), "test@eyebeem.com");
    }

    @Test
    public void getUser2_returnsJaneDoe() throws Exception {
        HttpResponse<String> res = api.get("/api/users/2");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(u.get("name").getAsString(), "Jane Doe");
        Assert.assertEquals(u.get("email").getAsString(), "jane@eyebeem.com");
    }

    @Test
    public void getUser3_returnsImaad() throws Exception {
        HttpResponse<String> res = api.get("/api/users/3");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(u.get("name").getAsString(), "imaad");
        Assert.assertEquals(u.get("email").getAsString(), "imaad@imaad.com");
    }

    @Test
    public void getUser4_returnsAryan() throws Exception {
        HttpResponse<String> res = api.get("/api/users/4");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(u.get("name").getAsString(), "aryan");
        Assert.assertEquals(u.get("email").getAsString(), "aryan@aryan.com");
    }

    @Test
    public void getUser5_returnsPetlover() throws Exception {
        HttpResponse<String> res = api.get("/api/users/5");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(u.get("name").getAsString(), "petlover");
        Assert.assertEquals(u.get("email").getAsString(), "petlover@pet.com");
    }

    @Test
    public void getUser_hasOrderHistory() throws Exception {
        HttpResponse<String> res = api.get("/api/users/1");
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertTrue(u.has("orderHistory"));
    }

    @Test
    public void getUser1_orderHistoryHasOneOrder() throws Exception {
        HttpResponse<String> res = api.get("/api/users/1");
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        var history = u.getAsJsonArray("orderHistory");
        Assert.assertEquals(history.size(), 1);
    }

    @Test
    public void getUser2_orderHistoryEmpty() throws Exception {
        HttpResponse<String> res = api.get("/api/users/2");
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        var history = u.getAsJsonArray("orderHistory");
        Assert.assertEquals(history.size(), 0);
    }
}
