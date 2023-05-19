package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = ".x_title h2")
    private WebElement dashboardsElm;

    @FindBy(linkText = "Dashboard")
    private WebElement dashboardsMenu;

    @FindBy(css = ".profile_info>h2")
    private WebElement welcomeElm;

    @FindBy(css = "a[class='user-profile dropdown-toggle']")
    private WebElement emailNav;

    @FindBy(css = ".menu-workspace")
    private WebElement workspaceNav;

    @FindBy(css = ".menu-home")
    private WebElement homeNav;

    @FindBy(css = "a[href$=Projects]")
    private WebElement processesMenu;

    @FindBy(css = "a[href$=Characteristics]")
    private WebElement characteristicsMenu;


    @FindBy(css = ".sidebar-footer.hidden-small>a[href=\"/Account/Logout\"]")
    private WebElement logoutBtn;

    public HomePage assertOnHomePage() {
        String txt = "Welcome";
        Assert.assertTrue(welcomeElm.isDisplayed(), "Welcome element is not displayed");
        Assert.assertTrue(welcomeElm.getText().contains(txt),
                "Welcome element text: "+ welcomeElm.getText() + "doesn't contain word "+ txt);
        Assert.assertTrue(driver.getCurrentUrl().contains("http://localhost:4444/"));

        return this;
    }

    public HomePage assertOnDashboardsPage() {
        String txt = "DEMO PROJECT";
        Assert.assertTrue(dashboardsElm.isDisplayed(), "Processes element is not displayed");
        Assert.assertTrue(dashboardsElm.getText().contains(txt),
                "Dashboards element text: "+ dashboardsElm.getText() + "doesn't contain word "+ txt);
        assertDashboardUrl("http://localhost:4444/");
        return this;
    }

    public HomePage assertDashboardUrl(String pageUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl);
        return this;
    }

    public HomePage assertOnHomePage(String userEmail) {
        Assert.assertTrue(emailNav.getText().contains(userEmail),
                "Incorrect username, doesn't contain "+ userEmail);

        return this;
    }

    public HomePage expandEmailNav(){
        emailNav.click();
        return this;
    }

    public LoginPage logoutViaButton(){
        logoutBtn.click();
        return new LoginPage(driver);
    }
    public ProcessesPage goToProcesses(){
        if (!isParentExpanded(workspaceNav)){
            workspaceNav.click();
//            #sidebar-menu > div > ul > li.active > ul > li.active > a
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(processesMenu));

        processesMenu.click();
        return new ProcessesPage(driver);
    }


    public CharacteristicsPage goToCharacteristics() {
        if (!isParentExpanded(workspaceNav)) {
            workspaceNav.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(characteristicsMenu));
        characteristicsMenu.click();
        return new CharacteristicsPage(driver);
    }

    public HomePage goToDashboards() {
        if(!isParentExpanded(homeNav)){
            homeNav.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(dashboardsMenu));
        dashboardsMenu.click();
        return this;
    }

    private boolean isParentExpanded(WebElement menuLink){
        WebElement parent = menuLink.findElement(By.xpath("./.."));
        return parent.getAttribute("class").contains("active");
    }
}
