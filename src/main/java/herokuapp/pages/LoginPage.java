package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "username")
    WebElement userName;
    @FindBy(id = "password")
    WebElement userPassword;

    public LoginPage enterPersonalData(String user, String password) {
        type(userName, "tomsmith");
        type(userPassword, "SuperSecretPassword!");
        return this;
    }
    public LoginPage enterPersonalWrongPasswordData(String user, String password) {
        type(userName, "tomsmith");
        type(userPassword, "WrongSecretPassword!");
        return this;
    }

    public LoginPage enterPersonalEmptyFieldsData(String user, String password) {
        type(userName, "");
        type(userPassword, "");
        return this;
    }

    @FindBy(tagName = "i")
    WebElement loginButton;
    public ProfilePage clickOnLoginButton() {
        click(loginButton);
        return new ProfilePage(driver,wait);
    }
}
