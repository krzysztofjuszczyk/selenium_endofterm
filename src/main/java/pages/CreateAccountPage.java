package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public LoginPage returnToLoginPage(){
        loginLnk.click();
        return new LoginPage(driver);
    }

}
