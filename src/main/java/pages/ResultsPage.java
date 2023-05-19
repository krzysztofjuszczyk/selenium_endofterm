package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends HomePage{

    @FindBy(linkText = "Add results sample")
    private WebElement addResultsSampleBtn;

    @FindBy(linkText = "Back to characteristics")
    private WebElement backToCharacteristicsBtn;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public CreateResultsPage clickAddResults(){
        addResultsSampleBtn.click();
        return new CreateResultsPage(driver);
    }

    public CharacteristicsPage backToCharacteristics(){
        backToCharacteristicsBtn.click();
        return new CharacteristicsPage(driver);
    }

}
