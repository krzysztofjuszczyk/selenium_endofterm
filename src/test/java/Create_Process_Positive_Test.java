import config.Config;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.UUID;

public class Create_Process_Positive_Test extends SeleniumBaseTest{
    @Test
    public void createProcessTest(){
        String randomName = UUID.randomUUID().toString().substring(0,10);
        String randomDesc = UUID.randomUUID().toString().substring(0,10);
        String randomNotes = UUID.randomUUID().toString().substring(0,10);

        new LoginPage(driver)
                .typeEmail(config.getAppUsername())
                .typePassword(config.getPassword())
                .submitLogin()
                .goToProcesses()
                .assertOnProcessesPage()
                .addNewProcess(randomName, randomDesc, randomNotes)
                .assertProcessHasBeenCreated(randomName, randomDesc,randomNotes);
    }

}
