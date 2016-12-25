package app.admin.features.steps.serenity;

import app.admin.pages.AdminLandingPage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminSteps {

    AdminLandingPage adminLandingPage;

    @Step
    public void logRole(String role) {
        System.out.println("User role is: ${role}");
    }

    @Step
    public void gotoFirstPage() {
        adminLandingPage.open();
    }

    @Step
    public void verifyPageContent(String message) {
        String welcomeMessage = adminLandingPage.welcomeMessage.getText();
        assertThat(welcomeMessage).isEqualTo(message);
    }
}
