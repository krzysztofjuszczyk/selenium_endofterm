package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CreateProcessPage extends HomePage{
    public CreateProcessPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "Name")
    private WebElement processNameTxt;

    @FindBy(id = "Description")
    private WebElement processDescriptionTxt;

    @FindBy(id = "Notes")
    private WebElement processNotesTxt;

    @FindBy(linkText = "Back to List")
    private WebElement backToListBtn;

    @FindBy(css = "button[type=reset]")
    private WebElement resetBtn;

    @FindBy(css = "input[type=submit]")
    private WebElement createBtn;

    @FindBy (css= ".text-danger.field-validation-error")
    private WebElement errorField;

    public CreateProcessPage typeName(String name)  {
        processNameTxt.clear();
        processNameTxt.sendKeys(name);
        return this;
    }

    public CreateProcessPage typeDescription (String desc){
        processDescriptionTxt.clear();
        processDescriptionTxt.sendKeys(desc);
        return this;
    }

    public CreateProcessPage typeNotes(String notes){
        processNotesTxt.clear();
        processNotesTxt.sendKeys(notes);
        return this;
    }

    public ProcessesPage createProcess(){
        createBtn.click();
        return new ProcessesPage(driver);
    }

    public CreateProcessPage createProcessWithFailure() {
        createBtn.click();
        return this;
    }

    public CreateProcessPage assertProcessNameError(String expErrorMessage) {
        Assert.assertEquals(errorField.getText(), expErrorMessage);
        return this;
    }


    public ProcessesPage backToList() {
        backToListBtn.click();
        return new ProcessesPage(driver);
    }
}
