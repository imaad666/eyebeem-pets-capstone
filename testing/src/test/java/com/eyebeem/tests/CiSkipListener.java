package com.eyebeem.tests;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

/**
 * Skips @DataDependent tests when running in CI (empty DB).
 * All tests remain in the suite so the report shows the full count (e.g. 121).
 */
public class CiSkipListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        if (!isCi()) return;

        Class<?> cls = method.getTestMethod().getTestClass().getRealClass();
        if (cls.isAnnotationPresent(DataDependent.class)) {
            throw new SkipException("Skipped in CI (requires pre-seeded data)");
        }
    }

    private static boolean isCi() {
        return "true".equalsIgnoreCase(System.getenv("CI"))
            || "true".equalsIgnoreCase(System.getProperty("ci"));
    }
}
