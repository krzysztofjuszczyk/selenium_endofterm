import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import pages.LoginPage;

public class Register_User_Positive_Test extends SeleniumBaseTest {

    //scenario 1
    @Test
    public void shouldRegisterUserCorrectly() {
        Faker fake = new Faker();
        String fakeEmail = fake.internet().emailAddress();


        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(fakeEmail)
                .typePassword("Test1!")
                .typeConfirmPassword("Test1!")
                .submitRegister()
                .assertOnHomePage()
                .assertOnHomePage(fakeEmail)
                .expandEmailNav();

    }
}
