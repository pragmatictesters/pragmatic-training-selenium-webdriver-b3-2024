package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = {"com.pragmatic.cucumber"},              // Package containing step definitions
        dryRun = false,
        tags = "@SmokeTest or @RegressionTest",
        name = {"Login"},
        plugin = {
                "pretty",                        // Prints Gherkin steps in the console
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json"      // JSON report for external integrations
        },
        monochrome = false                        // Makes the console output readable
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No additional methods requireds
}
