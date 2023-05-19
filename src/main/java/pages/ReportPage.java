package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ReportPage extends HomePage {
    public ReportPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//td[text()='Mean (x)']/../td[2]")
    private WebElement meanCell;

    @FindBy(xpath = "//td[text()='Lower Specification Limit (LSL)']/../td[2]")
    private WebElement lslCell;

    @FindBy(xpath = "//td[text()='Upper Specification Limit (USL)']/../td[2]")
    private WebElement uslCell;

    public ReportPage assertMean(String expMean) {
        Assert.assertEquals(meanCell.getText(), expMean);
        return this;
    }

    public ReportPage assertLsl(String expLsl) {

        Assert.assertEquals(lslCell.getText(), expLsl);
        return this;
    }

    public ReportPage assertUsl(String expUsl) {
        Assert.assertEquals(uslCell.getText(), expUsl);
        return this;
    }
}
