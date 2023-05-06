import config.Config;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeleniumBaseTest {
    protected WebDriver driver;
    protected Config config;

    @BeforeMethod
    public void baseBeforeMethod() {
        Config config = new Config();
    }

    @AfterMethod
    public void baseAfterMethod(ITestResult result) {
        //verify if test failed & create a file with a descriptive filename
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = "c:\\tmp\\";
            String className = this.getClass().getName();
            String methodName = result.getMethod().getMethodName();
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String extension = ".png";

            String destFilename = className +' '+ methodName +' '+ timeStamp + extension;
            createFile(path + destFilename);
        }

        driver.quit();
    }

    public void createFile(String destFilename) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(destFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
