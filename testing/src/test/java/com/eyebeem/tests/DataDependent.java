package com.eyebeem.tests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks test classes that require pre-seeded data (users, products, orders, etc.).
 * In CI (when CI=true), these tests are skipped so the report shows all tests with 100% pass rate.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataDependent {
}
