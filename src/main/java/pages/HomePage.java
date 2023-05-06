package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".profile_info>h2")
    WebElement welcomeElm;

    public HomePage assertOnHomePage() {
        String txt = "Welcome";
        Assert.assertTrue(welcomeElm.isDisplayed(), "Welcome element is not displayed");
        Assert.assertTrue(welcomeElm.getText().contains(txt),
                "Welcome element text: "+ welcomeElm.getText() + "doesn't contain word "+ txt);
        Assert.assertTrue(driver.getCurrentUrl().contains("http://localhost:4444/"));

        return this;
    }
}
