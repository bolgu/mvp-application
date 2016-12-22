package app.admin.features.steps;

import app.admin.features.steps.serenity.AdminSteps;
import cucumber.api.PendingException;;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class AdminLandingStepDefinitions {

    @Steps
    AdminSteps adminSteps;

    @Given("I am an '(.*)' of the application")
    public void asAnAdministrator(String role) {
//        throw new PendingException();
        adminSteps.logRole(role);
    }

    @And("I know the url of the administration application as '(.*)'")
    public void iKnowTheAdminPortalUrl(String url) {
        adminSteps.logUrl(url);
    }

    @When("I am landing on the first page")
    public void landingOnFirstPage() {
        adminSteps.gotoFirstPage();
    }

    @Then("I should see a welcome message '(.*)'")
    public void seeGiveInformation(String message) {
        adminSteps.verifyPageContent(message);
    }
}
