package com.backoffice.provider;

import com.backoffice.context.TestContext;

/**
 * @author Neha Kharbanda
 */
public class TestContextProvider {

    private static TestContext testContext;

    public TestContextProvider() {
    }

    public static TestContext getContext() {
        if (testContext == null) {
            testContext = new TestContext();
        }
        return testContext;
    }

}
