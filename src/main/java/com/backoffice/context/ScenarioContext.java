package com.backoffice.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Neha Kharbanda
 */
public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object obj) {
        scenarioContext.put(key, obj);
    }

    public Object getContext(String key) {
        return scenarioContext.get(key);
    }

}
