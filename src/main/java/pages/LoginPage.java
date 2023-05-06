package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;


public class LoginPage {
    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // elements on the page
    @FindBy(id = "Email")
    private WebElement emailTxt;

    @FindBy(id = "Password")
    private WebElement passwordTxt;

    @FindBy(css = "button[type=submit]")
    private WebElement loginBtn;

    @FindBy(css = "div.text-danger.validation-summary-errors > ul > li")
    private List<WebElement> loginErrors;

    @FindBy(css = "a[href=\"/Account/Register?returnurl=%2F\"")
    private WebElement registerBtn;

    public LoginPage typeEmail(String email) {
        emailTxt.clear();
        emailTxt.sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordTxt.clear();
        passwordTxt.sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        loginBtn.click();
        return new HomePage(driver);
    }

    public LoginPage submitLoginWithFaillure() {
        loginBtn.click();
        return this;
    }

    public CreateAccountPage goToRegisterPage() {
        registerBtn.click();
        return new CreateAccountPage(driver);
    }

    public LoginPage assertErrorIncorrectEmail() {
        Assert.assertTrue(!loginErrors.isEmpty());
        for (int i = 0; i < loginErrors.size(); i++) {
            String errorMessage = loginErrors.get(i).getText();
            Assert.assertTrue(errorMessage.contains("is not"));
        }
        return this;
    }

}
