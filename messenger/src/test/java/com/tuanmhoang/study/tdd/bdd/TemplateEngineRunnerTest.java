package com.tuanmhoang.study.tdd.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"classpath:features/TemplateEngine.feature"},
    glue = {"com.tuanmhoang.study.tdd.steps"})
public class TemplateEngineRunnerTest {
}
