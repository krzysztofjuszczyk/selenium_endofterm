import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import pages.LoginPage;

public class Register_User_Positive_Test extends SeleniumBaseTest{


    @Test
    public void shouldRegisterUserCorrectly(){
        Faker faker = new Faker();
        String fakeEmail = faker.internet().emailAddress();


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
