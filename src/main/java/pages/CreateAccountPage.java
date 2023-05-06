package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CreateAccountPage {
    protected WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "Email")
    private WebElement emailTxt;

    @FindBy(id = "Password")
    private WebElement passwordTxt;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTxt;

    @FindBy(css ="button[type=submit]")
    private WebElement registerBtn;

    @FindBy(css = "a[href=\"/Account/Login\"]")
    private WebElement loginLnk;

    @FindBy(css ="#register > section > form > h1")
    private WebElement createAccountElm;

    @FindBy(css = ".validation-summary-errors>ul>li")
    public List<WebElement> createAccountErrors;

    public CreateAccountPage typeEmail(String email) {
        emailTxt.clear();
        emailTxt.sendKeys(email);
        return this;
    }

    public CreateAccountPage typePassword(String password) {
        passwordTxt.clear();
        passwordTxt.sendKeys(password);
        return this;
    }

    public CreateAccountPage typeConfirmPassword(String password) {
        confirmPasswordTxt.clear();
        confirmPasswordTxt.sendKeys(password);
        return this;
    }

    public HomePage submitRegister() {
        registerBtn.click();
        return new HomePage(driver);
    }

    public CreateAccountPage submitRegisterWithFailure() {
        registerBtn.click();
        return this;
    }

    public LoginPage returnToLoginPage(){
        loginLnk.click();
        return new LoginPage(driver);
    }

    public CreateAccountPage assertOnRegisterPage() {
        String txt = "Create Account";
        Assert.assertTrue(createAccountElm.isDisplayed(), "Create account element is not displayed");
        Assert.assertTrue(createAccountElm.getText().contains(txt),
                "Create Account element text: "+ createAccountElm.getText() + "doesn't contain: "+ txt);
        Assert.assertTrue(driver.getCurrentUrl().contains("/Register"));

        return this;
    }

    public CreateAccountPage assertErrorPasswordDoesnotMatch(){
        String expectedErrorMessage = "The password and confirmation password do not match.";

        Assert.assertTrue(!createAccountErrors.isEmpty());
        boolean containsError = false;
        for (int i = 0; i < createAccountErrors.size(); i++) {
            String actualErrorMsg = createAccountErrors.get(i).getText();
            if(actualErrorMsg.equals(expectedErrorMessage)){
                containsError = true;
            }
            }
        Assert.assertTrue(containsError);

        return this;
    }
}
