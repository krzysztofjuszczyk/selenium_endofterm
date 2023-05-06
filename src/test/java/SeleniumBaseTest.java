import config.Config;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SeleniumBaseTest {
    protected WebDriver driver;
    protected Config config = new Config();

    @BeforeMethod
    public void baseBeforeMethod() {
        System.setProperty("webdriver.chrome.driver", "c:/dev/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(config.getURL());

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

//        driver.quit();
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
