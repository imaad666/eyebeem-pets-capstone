package com.eyebeem.tests.api;

import com.eyebeem.tests.BaseApiTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.http.HttpResponse;

/**
 * API tests for products endpoint.
 */
public class ProductsApiTest extends BaseApiTest {

    @Test
    public void getProducts_returnsThree() throws Exception {
        HttpResponse<String> res = api.get("/api/products");
        Assert.assertEquals(res.statusCode(), 200);
        JsonArray arr = api.parseJsonArray(res.body());
        Assert.assertEquals(arr.size(), 3);
    }

    @Test
    public void getProducts_hasECat() throws Exception {
        HttpResponse<String> res = api.get("/api/products");
        JsonArray arr = api.parseJsonArray(res.body());
        String names = "";
        for (JsonElement e : arr) names += e.getAsJsonObject().get("name").getAsString() + ",";
        Assert.assertTrue(names.contains("e-CAT"));
    }

    @Test
    public void getProducts_hasEDog() throws Exception {
        HttpResponse<String> res = api.get("/api/products");
        JsonArray arr = api.parseJsonArray(res.body());
        String names = "";
        for (JsonElement e : arr) names += e.getAsJsonObject().get("name").getAsString() + ",";
        Assert.assertTrue(names.contains("e-DOG"));
    }

    @Test
    public void getProducts_hasEHamster() throws Exception {
        HttpResponse<String> res = api.get("/api/products");
        JsonArray arr = api.parseJsonArray(res.body());
        String names = "";
        for (JsonElement e : arr) names += e.getAsJsonObject().get("name").getAsString() + ",";
        Assert.assertTrue(names.contains("e-HAMSTER"));
    }

    @Test
    public void getProduct1_returnsECat() throws Exception {
        HttpResponse<String> res = api.get("/api/products/1");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject p = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(p.get("name").getAsString(), "e-CAT");
        Assert.assertEquals(p.get("productId").getAsInt(), 1);
    }

    @Test
    public void getProduct1_price99_99() throws Exception {
        HttpResponse<String> res = api.get("/api/products/1");
        JsonObject p = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(p.get("price").getAsDouble(), 99.99, 0.01);
    }

    @Test
    public void getProduct2_returnsEDog() throws Exception {
        HttpResponse<String> res = api.get("/api/products/2");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject p = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(p.get("name").getAsString(), "e-DOG");
        Assert.assertEquals(p.get("productId").getAsInt(), 2);
    }

    @Test
    public void getProduct2_price129_99() throws Exception {
        HttpResponse<String> res = api.get("/api/products/2");
        JsonObject p = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(p.get("price").getAsDouble(), 129.99, 0.01);
    }

    @Test
    public void getProduct3_returnsEHamster() throws Exception {
        HttpResponse<String> res = api.get("/api/products/3");
        Assert.assertEquals(res.statusCode(), 200);
        JsonObject p = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(p.get("name").getAsString(), "e-HAMSTER");
        Assert.assertEquals(p.get("productId").getAsInt(), 3);
    }

    @Test
    public void getProduct3_price49_99() throws Exception {
        HttpResponse<String> res = api.get("/api/products/3");
        JsonObject p = api.parseJson(res.body()).getAsJsonObject();
        Assert.assertEquals(p.get("price").getAsDouble(), 49.99, 0.01);
    }

}
