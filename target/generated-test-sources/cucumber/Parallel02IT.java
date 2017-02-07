import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true,
features = {"/Users/Amit/git-repositories/allure-reporting-demo/src/test/resources/features/allure_reporting_demo.feature:19"},
plugin = {"ru.yandex.qatools.allure.cucumberjvm.AllureReporter:target/cucumber-parallel/2.ru.yandex.qatools.allure.cucumberjvm.AllureReporter"},
monochrome = true,
glue = { "com.amitsh.allure" })
public class Parallel02IT {
}
