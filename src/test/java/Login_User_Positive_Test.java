import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_User_Positive_Test extends SeleniumBaseTest {


    @DataProvider(name = "registeredUsersList")
    public Object[][] setup() {

        // how many fake users do we want to generate

        Object[][] users = new Object[Constants_For_Tests.usersQty][2];
        Faker fake = new Faker();

        for (int i = 0; i < Constants_For_Tests.usersQty; i++) {
            String email = fake.internet().emailAddress();
            String pass = generateStrongPassword();
            users[i][0] = email;
            users[i][1] = pass;

        }
        return users;
    }

    // scenario 4 - should login correctly
    @Test(dataProvider = "registeredUsersList")
    public void shouldLoginCorrectlyExistingUser(String email, String password) {
        new LoginPage(driver)
                // registering a new user
                .goToRegisterPage()
                .typeEmail(email)
                .typePassword(password)
                .typeConfirmPassword(password)
                .submitRegister()
                .assertOnHomePage(email)
                .logoutViaButton()

                // logging in to user account created in previous step
                .typeEmail(email)
                .typePassword(password)
                .submitLogin()
                .assertOnHomePage(email);
    }

    private static String generateStrongPassword() {
        var password = new Faker().internet()
        .password(Constants_For_Tests.minGeneratedPassLength, Constants_For_Tests.maxGeneratedPassLength, true, true, true);

        if (isPasswordValid(password)) {
            return password;
        }
        //  tries to generate again if the password is invalid
        return generateStrongPassword();
    }

    private static boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(Constants_For_Tests.regEx);  // list of special chars in a form of regex
        Matcher matcher = pattern.matcher(password);

        return password.length() >= Constants_For_Tests.minGeneratedPassLength &&
                password.length() <= Constants_For_Tests.maxGeneratedPassLength &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isUpperCase)
                && matcher.find();
    }

}
