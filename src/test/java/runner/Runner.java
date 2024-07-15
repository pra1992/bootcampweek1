package runner;

import org.testng.annotations.DataProvider;

import bootcamp3pom.pages.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/main/resources/features/Accounts.feature",
                 glue = "",
                 dryRun = true
		)
public class Runner extends BaseClass {

	@DataProvider(parallel = true)
    public Object[][] scenarios() {
		return super.scenarios();
		
	}
}
