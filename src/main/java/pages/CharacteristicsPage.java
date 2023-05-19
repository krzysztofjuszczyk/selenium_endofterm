package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CharacteristicsPage extends HomePage {
    public CharacteristicsPage(WebDriver driver) {super(driver);}

    private String GENERIC_CHARACTERISTIC_ROW_XPATH = "//td[text()='%s']/..";
    private String GENERIC_CHARACTERISTIC_RESULTS_XPATH = "//td[text()='%s']/..//a[contains(@href,'Results')]";
    private String GENERIC_CHARACTERISTIC_REPORT_XPATH = "//td[text()='%s']/..//a[contains(@href,'Report')]";

    @FindBy(css = ".page-title h3")
    private WebElement characteristicsElm;

    @FindBy(linkText = "Add new characteristic")
    private WebElement addNewCharacteristicBtn;


//    public CharacteristicsPage addNewCharacteristic(String process, String name, String lsl, String usl, String binCount) {
//            clickAddCharacteristic()
//                    .selectProcess(process)
//                .typeName(name)
//                    .typeLsl(lsl)
//                    .typeUsl(usl)
////                    .typeBinCount(binCount)
//                .submitCreateCharacteristic();
//
//        return this;
//    }
    public CreateCharacteristicPage clickAddCharacteristic() {
        addNewCharacteristicBtn.click();
        return new CreateCharacteristicPage(driver);
    }

    public ResultsPage clickResultsPage(String name) {
        String resultsBtnXpath = String.format(GENERIC_CHARACTERISTIC_RESULTS_XPATH, name);
        WebElement resultsButton = driver.findElement(By.xpath(resultsBtnXpath));
        resultsButton.click();
        return new ResultsPage(driver);
    }

    public ReportPage clickReportPage(String name) {
        String reportBtnXpath = String.format(GENERIC_CHARACTERISTIC_REPORT_XPATH, name);
        WebElement reportButton = driver.findElement(By.xpath(reportBtnXpath));
        reportButton.click();
        return new ReportPage(driver);
    }


    public CharacteristicsPage assertCharacteristic(String expName, String expLsl, String expUsl, String expBinCount) {
        String characteristicXpath = String.format(GENERIC_CHARACTERISTIC_ROW_XPATH, expName);
        WebElement characteristicRow = driver.findElement(By.xpath(characteristicXpath));

        String actLsl = characteristicRow.findElement(By.xpath("./td[3]")).getText();
        String actUsl = characteristicRow.findElement(By.xpath("./td[4]")).getText();
        String actBinCount = characteristicRow.findElement(By.xpath("./td[5]")).getText();

        Assert.assertEquals(actLsl, expLsl);
        Assert.assertEquals(actUsl, expUsl);
        Assert.assertEquals(actBinCount, expBinCount);

        return this;
    }

    public CharacteristicsPage assertCharacteristicIsNotShown(String characteristicName) {
        String characteristicXpath = String.format(GENERIC_CHARACTERISTIC_ROW_XPATH, characteristicName);
        List<WebElement> processesWithGivenName = driver.findElements(By.xpath(characteristicXpath));
        Assert.assertEquals(processesWithGivenName.size(), 0);
        return this;
    }

    public CharacteristicsPage assertOnCharacteristicsPage() {
        String txt = "Characteristics";

        Assert.assertTrue(characteristicsElm.isDisplayed(), "Processes element is not displayed");
        Assert.assertTrue(characteristicsElm.getText().contains(txt),
                "Characteristics element text: " + characteristicsElm.getText() + "doesn't contain word " + txt);
        assertDashboardUrl("http://localhost:4444/Characteristics");
        return this;
    }

    public CharacteristicsPage assertDashboardUrl(String pageUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl);
        return this;
    }
}
