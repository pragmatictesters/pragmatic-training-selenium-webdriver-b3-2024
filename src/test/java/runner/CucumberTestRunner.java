package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (
        features = {"src/test/resources/features/sauce-login.feature"},
        glue = {"com.pragmatic.steps"},
        plugin = {
                "pretty",                        // Prints Gherkin steps in the console
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json"      // JSON report for external integrations
        },
        monochrome = true,
        tags = "@SmokeTest"

)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    //NO code is required here
}
