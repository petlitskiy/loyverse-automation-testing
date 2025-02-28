package com.backoffice.context;

import com.backoffice.managers.IPageObjectManager;
import com.backoffice.managers.ISharedFileManager;
import com.backoffice.managers.RestServiceManager;
import com.backoffice.managers.SharedFileManager;

/**
 * @author Neha Kharbanda
 */
public class TestContext {

    private static IPageObjectManager pageObjectManager;
    private static ISharedFileManager sharedFileManager;
    private static RestServiceManager restServiceManager;

    public ScenarioContext scenarioContext;


    public TestContext() {
        sharedFileManager = new SharedFileManager();
        restServiceManager = new RestServiceManager();
        scenarioContext = new ScenarioContext();
    }


    public IPageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }


    public ISharedFileManager getSharedFileManager() {
        return sharedFileManager;
    }

    public RestServiceManager getRestServiceManager() {
        return restServiceManager;
    }


    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}
