package todopal.features;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "junit:build/cucumber-junit.xml", "html:build/cucumber.html"},

        features = "src/test/resources/ID010_Deny_Student_Task_Completion.feature"

)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class CucumberTestsRunner {
}