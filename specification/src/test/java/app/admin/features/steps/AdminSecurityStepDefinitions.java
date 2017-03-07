package app.admin.features.steps;

import app.admin.features.steps.serenity.AdminSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;

//gradle clean test aggregate -Dcucumber.options="--tags feature:AdminSecurity"
@WithTag(type="feature", name="AdminSecurity")
public class AdminSecurityStepDefinitions {

    @Steps
    AdminSteps adminSteps;

//    Scenario: Admin site login page

    @Given("I am a '(.*)' of the admin application")
    public void asAnApplicationUser(String role) {
        adminSteps.logRole(role);
    }

    @When("I am landing on the main page")
    public void landingOnMainPage() {
        adminSteps.gotoFirstPage();
    }

    @Then("I should see a '(.*)' form")
    public void seeLoginForm(String message) {
        adminSteps.verifyPageContentHasForm(message);
    }

//    Scenario: Login in application as admin

    @Given("I am an '(.*)' of the application")
    public void asAnAdministrator(String role) {
        adminSteps.logRole(role);
    }

    @When("I am logging in the application")
    public void loggingInTheApplication() {
        throw new PendingException();
    }

    @Then("I should see the '(.*)'")
    public void seePageMessage(String message) {
        throw new PendingException();
    }

//    Scenario: Logout from application

//    @Given("I am an '.*' of the application")
//    public void asAnAdministrator(String role) {
//        adminSteps.logRole(role);
//    }

    @And("I am logged into the application")
    public void loggedAsAdmin() {
        throw new PendingException();
    }

    @When("I logout from application")
    public void seeLandingPageMessage(String message) {
        throw new PendingException();
    }

    @Then("I should see the message '.*'")
    public void seeLandingPage(String message) {
        throw new PendingException();
    }
}
