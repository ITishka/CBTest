package ru.kos.ve;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/ru/kos/ve",
        glue = "ru.kos.ve",
        tags = "@all",
        snippets = SnippetType.CAMELCASE

)
public class RunCbTest {
}

