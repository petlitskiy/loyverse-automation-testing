package com.backoffice.config;

import com.backoffice.utilities.AnnotationExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonGenerator {

    public static String toRequest(Object object) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
        return gson.toJson(object);
    }

    public static Gson toGson() {
        return new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
    }
}
