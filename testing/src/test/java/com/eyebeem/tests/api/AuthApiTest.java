package com.eyebeem.tests.api;

import com.eyebeem.tests.BaseApiTest;
import com.eyebeem.tests.util.TestDataLoader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * API tests for login: each account login and response validation.
 */
public class AuthApiTest extends BaseApiTest {

    @Test
    public void login_user1_TestUser() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"Test User\",\"email\":\"test@eyebeem.com\"}");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(body.get("id").getAsLong(), 1);
        Assert.assertEquals(body.get("name").getAsString(), "Test User");
        Assert.assertEquals(body.get("email").getAsString(), "test@eyebeem.com");
        Assert.assertTrue(body.has("orderHistory"));
    }

    @Test
    public void login_user2_JaneDoe() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"Jane Doe\",\"email\":\"jane@eyebeem.com\"}");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(body.get("id").getAsLong(), 2);
        Assert.assertEquals(body.get("name").getAsString(), "Jane Doe");
        Assert.assertEquals(body.get("email").getAsString(), "jane@eyebeem.com");
    }

    @Test
    public void login_user3_imaad() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"imaad\",\"email\":\"imaad@imaad.com\"}");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(body.get("id").getAsLong(), 3);
        Assert.assertEquals(body.get("name").getAsString(), "imaad");
        Assert.assertEquals(body.get("email").getAsString(), "imaad@imaad.com");
    }

    @Test
    public void login_user4_aryan() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"aryan\",\"email\":\"aryan@aryan.com\"}");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(body.get("id").getAsLong(), 4);
        Assert.assertEquals(body.get("name").getAsString(), "aryan");
        Assert.assertEquals(body.get("email").getAsString(), "aryan@aryan.com");
    }

    @Test
    public void login_user5_petlover() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"petlover\",\"email\":\"petlover@pet.com\"}");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(body.get("id").getAsLong(), 5);
        Assert.assertEquals(body.get("name").getAsString(), "petlover");
        Assert.assertEquals(body.get("email").getAsString(), "petlover@pet.com");
    }

    @Test
    public void loginResponse_hasId() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"Test User\",\"email\":\"test@eyebeem.com\"}");
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertTrue(body.has("id"));
        Assert.assertTrue(body.get("id").isJsonPrimitive());
    }

    @Test
    public void loginResponse_hasName() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"Test User\",\"email\":\"test@eyebeem.com\"}");
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertTrue(body.has("name"));
        Assert.assertFalse(body.get("name").getAsString().isEmpty());
    }

    @Test
    public void loginResponse_hasEmail() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"Test User\",\"email\":\"test@eyebeem.com\"}");
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertTrue(body.has("email"));
        Assert.assertTrue(body.get("email").getAsString().contains("@"));
    }

    @Test
    public void loginResponse_hasOrderHistory() throws Exception {
        HttpResponse<String> res = api.post("/api/users/login", "{\"name\":\"Test User\",\"email\":\"test@eyebeem.com\"}");
        JsonObject body = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertTrue(body.has("orderHistory"));
        Assert.assertTrue(body.get("orderHistory").isJsonArray());
    }

    @Test
    public void loginAsEachUser_fromTestData() throws Exception {
        JsonArray users = TestDataLoader.loadUsers();
        for (JsonElement u : users) {
            JsonObject user = u.getAsJsonObject();
            String name = user.get("name").getAsString();
            String email = user.get("email").getAsString();
            HttpResponse<String> res = api.post("/api/users/login",
                String.format("{\"name\":\"%s\",\"email\":\"%s\"}", name.replace("\"", "\\\""), email.replace("\"", "\\\"")));
            Assert.assertEquals(res.statusCode(), 200, "Login failed for " + email);
            JsonObject body = api.parseJson(res.body()).getAsJsonObject();
            Assert.assertEquals(body.get("email").getAsString(), email);
        }
    }
}
