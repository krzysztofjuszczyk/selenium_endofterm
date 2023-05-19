package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CreateCharacteristicPage extends HomePage{
    public CreateCharacteristicPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ProjectId")
    private WebElement projectSlc;

    @FindBy(id = "Name")
    private WebElement charactNameTxt;

    @FindBy(id = "LowerSpecificationLimit")
    private WebElement charactLslTxt;

    @FindBy(id = "UpperSpecificationLimit")
    private WebElement charactUslTxt;

    @FindBy(id = "HistogramBinCount")
    private  WebElement charactHistogramTxt;

    @FindBy(linkText = "Back to List")
    private WebElement backToListBtn;

    @FindBy(css = "button[type=reset]")
    private WebElement resetBtn;

    @FindBy(css = "input[type=submit]")
    private WebElement createBtn;

    @FindBy (css= ".text-danger.field-validation-error")
    private WebElement errorField;

    public CreateCharacteristicPage typeName(String name)  {
        charactNameTxt.clear();
        charactNameTxt.sendKeys(name);
        return this;
    }

    public CreateCharacteristicPage typeUsl(String name)  {
        charactUslTxt.clear();
        charactUslTxt.sendKeys(name);
        return this;
    }

    public CreateCharacteristicPage typeLsl(String name)  {
        charactLslTxt.clear();
        charactLslTxt.sendKeys(name);
        return this;
    }

    public CreateCharacteristicPage selectProcess(String processName){
        new Select(projectSlc).selectByVisibleText(processName);
        return this;
    }


    public CharacteristicsPage submitCreateCharacteristic(){
        createBtn.click();
        return new CharacteristicsPage(driver);
    }

    public CreateCharacteristicPage submitCreateWithFailure(){
        createBtn.click();
        return new CreateCharacteristicPage(driver);
    }

    public CreateCharacteristicPage assertProcessError(String expErrorMessage){
        Assert.assertEquals(errorField.getText(), expErrorMessage);
        return this;
    }


    public CharacteristicsPage backToList() {
        backToListBtn.click();
        return new CharacteristicsPage(driver);
    }
}
