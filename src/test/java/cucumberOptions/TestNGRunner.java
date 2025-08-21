package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features ={"src/test/java/features"},
        tags="@Test",  // tags="not @Regression" //tags = "@AdminLogin and @Test"
        glue= {"stepDefinitions"},
        monochrome = true,
        plugin = {"pretty",
                "rerun:test-output/failed_scenarios.txt",
                "html:test-output/cucumber.html",
                "json:test-output/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel=false)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}

