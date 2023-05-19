package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ProcessesPage extends HomePage{
//    protected WebDriver driver;
    private String GENERIC_PROCESS_ROW_XPATH = "//td[text()='%s']/..";

    @FindBy (css = ".page-title h3")
    private WebElement processesElm;

    @FindBy(linkText = "Add new process")
    private WebElement addNewProcessBtn;

    public ProcessesPage(WebDriver driver) {super(driver);}

    public ProcessesPage assertOnProcessesPage() {
        String txt = "Processes";

        Assert.assertTrue(processesElm.isDisplayed(), "Processes element is not displayed");
        Assert.assertTrue(processesElm.getText().contains(txt),
                "Processes element text: "+ processesElm.getText() + "doesn't contain word "+ txt);
        assertProcessesUrl("http://localhost:4444/Projects");

        return this;
    }

    public ProcessesPage assertProcessesUrl(String pageUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl);
        return this;
    }

    public ProcessesPage addNewProcess(String name, String desc, String notes) {

                goToCreateProcessPage()
                    .typeName(name)
                    .typeDescription(desc)
                    .typeNotes(notes)
                .createProcess()
                ;

        return this;
    }

    public CreateProcessPage goToCreateProcessPage(){
        addNewProcessBtn.click();
        return new CreateProcessPage(driver);
    }

    public ProcessesPage assertProcessHasBeenCreated(String expectedName, String expDescription, String expNotes){

        String processXpath = String.format(GENERIC_PROCESS_ROW_XPATH, expectedName);
        WebElement processRow = driver.findElement(By.xpath(processXpath));

        String actDescription = processRow.findElement(By.xpath("./td[2]")).getText();

        String actNotes = processRow.findElement(By.xpath("./td[3]")).getText();

        Assert.assertEquals(actDescription,expDescription);
        Assert.assertEquals(actNotes,expNotes);

        return this;
    }

    public ProcessesPage assertProcessIsNotShown(String processName) {
        String processXpath = String.format(GENERIC_PROCESS_ROW_XPATH,processName);
        List<WebElement> processesWithGivenName = driver.findElements(By.xpath(processXpath));
        Assert.assertEquals(processesWithGivenName.size(),0);
        return this;
    }

}
