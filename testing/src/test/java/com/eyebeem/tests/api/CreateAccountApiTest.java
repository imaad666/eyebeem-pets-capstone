package com.eyebeem.tests.api;

import com.eyebeem.tests.BaseApiTest;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;

/**
 * E2E API test: create new account via login.
 * Does NOT place order or alter inventory.
 */
public class CreateAccountApiTest extends BaseApiTest {

    @Test
    public void createNewAccount_viaLogin_returnsUserWithId() throws Exception {
        String uniqueEmail = "newuser" + System.currentTimeMillis() + "@test.com";
        HttpResponse<String> res = api.post("/api/users/login",
            "{\"name\":\"New Test User\",\"email\":\"" + uniqueEmail + "\"}");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject u = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertTrue(u.get("id").getAsLong() > 0);
        Assert.assertEquals(u.get("name").getAsString(), "New Test User");
        Assert.assertEquals(u.get("email").getAsString(), uniqueEmail);
        Assert.assertTrue(u.has("orderHistory"));
        Assert.assertEquals(u.getAsJsonArray("orderHistory").size(), 0);
    }
}
