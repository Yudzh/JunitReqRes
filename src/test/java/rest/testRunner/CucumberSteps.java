package rest.testRunner;

import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CucumberSteps implements En {

    public CucumberSteps() {
        Given("^Start$", () -> {
            log.info("Step Start from " + Thread.currentThread().getId());
            Thread.sleep(2000);
        });
        When("^Next$", () -> {
            Thread.sleep(2000);

            log.info("Step Next from " + Thread.currentThread().getId());
        });
        Then("^Finish$", () -> {
            log.info("Step Finish from " + Thread.currentThread().getId());
            Thread.sleep(2000);
        });
    }
}
