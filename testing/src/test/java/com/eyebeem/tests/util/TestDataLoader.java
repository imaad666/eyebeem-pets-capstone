package com.eyebeem.tests.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads test data from db/*.json resources (synced from db test/).
 */
public class TestDataLoader {

    private static final Gson GSON = new Gson();

    public static JsonArray loadJsonArray(String resourcePath) {
        try (Reader r = new InputStreamReader(
                TestDataLoader.class.getResourceAsStream(resourcePath), StandardCharsets.UTF_8)) {
            return GSON.fromJson(r, JsonArray.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + resourcePath, e);
        }
    }

    public static JsonArray loadUsers() {
        return loadJsonArray("/db/users.json");
    }

    public static JsonArray loadOrders() {
        return loadJsonArray("/db/orders.json");
    }

    public static JsonArray loadOrderItems() {
        return loadJsonArray("/db/orderitems.json");
    }

    public static JsonArray loadInventory() {
        return loadJsonArray("/db/inventory.json");
    }

    /** Get user ids as list. */
    public static List<Long> getUserIds() {
        List<Long> out = new ArrayList<>();
        for (JsonElement e : loadUsers()) {
            out.add(e.getAsJsonObject().get("id").getAsLong());
        }
        return out;
    }

    /** Get order ids for a user from orders.json. */
    public static List<Long> getOrderIdsForUser(long userId) {
        List<Long> out = new ArrayList<>();
        for (JsonElement e : loadOrders()) {
            if (e.getAsJsonObject().get("user_id").getAsLong() == userId) {
                out.add(e.getAsJsonObject().get("id").getAsLong());
            }
        }
        return out;
    }
}
