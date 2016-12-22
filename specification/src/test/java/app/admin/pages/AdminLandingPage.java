package app.admin.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@DefaultUrl("http://localhost:8080")
public class AdminLandingPage extends PageObject {

    @FindBy(tagName="h1")
    public WebElement welcomeMessage;

    @WhenPageOpens
    public void waitUntilTitleAppears() {
        element(welcomeMessage).waitUntilVisible();
    }

    public AdminLandingPage(WebDriver driver) {
        super(driver);
    }

}
