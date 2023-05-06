import org.testng.annotations.Test;
import pages.LoginPage;

public class Register_User_Positive_Test extends SeleniumBaseTest{
    @Test
    public void shouldRegisterUserCorrectly(){
        new LoginPage(driver)
            .goToRegisterPage()
            .typeEmail("abcd@gmail.com")
                .typePassword("Test1!")
                .typeConfirmPassword("Test1!")
                .submitRegister()
                .assertOnHomePage();

    }
}
