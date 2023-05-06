import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Register_User_Negative_Test extends SeleniumBaseTest{
@Test
    public void shouldNotRegisterUserWhenPasswordDoesNotMatchConfirmation(){
    Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String password ="Abca1!";

    new LoginPage(driver)
            .goToRegisterPage()
            .typeEmail(email.replace('@','_'))
            .typePassword(password)
            .typeConfirmPassword(password.substring(1))
            .submitRegisterWithFailure()
            .submitRegisterWithFailure()
            .assertOnRegisterPage()
            .assertErrorPasswordDoesnotMatch();

}
}
