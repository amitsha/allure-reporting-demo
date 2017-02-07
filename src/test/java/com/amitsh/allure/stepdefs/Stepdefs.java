package com.amitsh.allure.stepdefs;

import com.amitsh.allure.Calculator;
import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import java.util.List;

import static com.google.common.truth.Truth.assertWithMessage;

@ScenarioScoped
public class Stepdefs {

    private final Calculator calculator;
    private int total;
    private String errorMessage;

    @Inject
    public Stepdefs(Calculator calculator) {
        this.calculator = calculator;

    }

    @When("^I add the following numbers:$")
    public void iAddTheFollowingNumbers(List<Integer> numbers) throws Throwable {
        try {
            total = calculator.add(numbers);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }

    }

    @Then("^total should be (\\d+)$")
    public void totalShouldBe(int total) throws Throwable {
        assertWithMessage("Total is not correct").that(total == this.total).isTrue();
    }

    @Then("^the error message \"(.*?)\" should be displayed$")
    public void theErrorMessageShouldBeDisplayed(String errorMessage) throws Throwable {
        assertWithMessage("Correct error message isn't displayed").that(errorMessage).isEqualTo(this.errorMessage);
    }
}