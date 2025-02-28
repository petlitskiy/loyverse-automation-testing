package com.backoffice.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (features = "classpath:features"
                , glue = {"com.backoffice.stepdefinitions"}
                , plugin = {"json:target/cucumber-report/cucumber.json", "html:target/html-report/cucumber.html"}
                , tags = "@Regression"
                , monochrome = true
        )

public class Runner extends AbstractTestNGCucumberTests {

}
