import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class Home_Page_Links_Test extends SeleniumBaseTest{


    //scenario 5
    @Test
    public void linksShouldRedirectToCorrectURL (){
        new LoginPage(driver)
                .typeEmail(config.getAppUsername())
                .typePassword(config.getPassword())
                .submitLogin()
                .assertOnHomePage(config.getAppUsername())
                .goToProcesses().assertOnProcessesPage()
                .goToDashboards().assertOnDashboardsPage()
                .goToCharacteristics().assertOnCharacteristicsPage()
                .goToDashboards().assertOnDashboardsPage()
                .goToDashboards().goToProcesses().assertOnProcessesPage()
                .goToDashboards().goToCharacteristics().assertOnCharacteristicsPage();



    }
}
