package herokuapp.form_authentication;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getAuthForm();
    }

    @Test
    public void loginPositiveTest() {
        new LoginPage(app.driver, app.wait)
                .enterPersonalData("tomsmith", "SuperSecretPassword!")
                .clickOnLoginButton()
                .verifySuccessfulLogin();
    }

    @Test
    public void loginNegativePasswordTest() {
        new LoginPage(app.driver, app.wait)
                .enterPersonalWrongPasswordData("tomsmith", "wrongPassword")
                .clickOnLoginButton()
                .verifyUnSuccessfulLogin();
    }

    @Test
    public void loginNegativeEmptyFieldsTest() {
        new LoginPage(app.driver, app.wait)
                .enterPersonalEmptyFieldsData("", "")
                .clickOnLoginButton()
                .verifyUnSuccessfulLogin();
    }
}
