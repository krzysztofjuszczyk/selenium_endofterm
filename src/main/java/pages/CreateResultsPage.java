package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateResultsPage extends HomePage{
    public CreateResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#Sample")
    private WebElement sampleTxt;

    @FindBy(css = "#Values")
    private WebElement resultsTxt;

    @FindBy(css = "input[type=submit]")
    private WebElement createBtn;

    @FindBy(css = "button[type=button]")
    private WebElement cancelCrossBtn;

    @FindBy(linkText = "Cancel")
    private WebElement cancelBtn;

    public CreateResultsPage typeSampleTxt(String sampleName){
        sampleTxt.clear();
        sampleTxt.sendKeys(sampleName);
        return this;
    }

    public CreateResultsPage typeResults (String resultText){
        resultsTxt.clear();
        resultsTxt.sendKeys(resultText);
        return this;
    }

    public ResultsPage submitCreate(){
        createBtn.click();
        return new ResultsPage(driver);
    }

}
