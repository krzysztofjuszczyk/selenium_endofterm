import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;


public class Register_User_Negative_Test extends SeleniumBaseTest {
    //scenario 2
    @Test
    public void shouldNotRegisterUserWhenPasswordDoesNotMatchConfirmation() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "Abca1!";

        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(email.replace('@', '_'))
                .typePassword(password)
                .typeConfirmPassword(password.substring(1))
                .submitRegisterWithFailure()
                .submitRegisterWithFailure()
                .assertOnRegisterPage()
                .assertErrorPasswordDoesnotMatch();

    }

    // scenario 3
    @Test(dataProvider = "incorrectPasswords")
    public void shouldNotRegisterUserWhenIncorrectPassword(String incorrectPassword, String expErrorMessage) {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();

        new LoginPage(driver)
                .goToRegisterPage()
                .typeEmail(email)
                .typePassword(incorrectPassword)
                .typeConfirmPassword(incorrectPassword)
                .submitRegisterWithFailure()
                .assertOnRegisterPage()
                .assertErrorIncorrectPassword(expErrorMessage);
    }

    @DataProvider(name = "incorrectPasswords")
    public Object[][] returnIncPasswords() {
        Faker fake = new Faker();
        int passQty = 10; // will be increased to divisible by 4
        int minPasswordLength = 6; // min length of correct password
        int maxPasswordLength = 20; // max length of the password, not to exagerrate

        if (passQty % 4 != 0) {
            passQty += passQty % 4;
        }

        Object[][] passwords = new Object[passQty][2]; // an array to hold the passwords, pass + comment

        for (int i = 0; i < passQty; i += 4) {
            String password = fake.internet().password(minPasswordLength, maxPasswordLength, false, true, true);
            passwords[i][0] = password;
            passwords[i][1] = "Passwords must have at least one uppercase ('A'-'Z').";

            password = fake.internet().password(minPasswordLength, maxPasswordLength, true, true, false);
            passwords[i + 1][0] = password;
            passwords[i + 1][1] = "Passwords must have at least one digit ('0'-'9').";


            password = fake.internet().password(minPasswordLength, maxPasswordLength, true, false, true);
            passwords[i + 2][0] = password;
            passwords[i + 2][1] = "Passwords must have at least one non alphanumeric character.";

            password = fake.internet().password(4, minPasswordLength - 1, true, true, true);
            passwords[i + 3][0] = password;
            passwords[i + 3][1] = "The Password must be at least 6 and at max 100 characters long.";
        }

        return passwords;
    }


}
