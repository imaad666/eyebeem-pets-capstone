package com.eyebeem.tests;

import com.eyebeem.tests.util.ApiClient;

/**
 * Base for backend API tests. Requires backend running at apiBaseUrl (default localhost:8081).
 */
public abstract class BaseApiTest {

    protected static final String API_BASE_URL = System.getProperty("apiBaseUrl", "http://localhost:8081");
    protected final ApiClient api = new ApiClient(API_BASE_URL);
}
