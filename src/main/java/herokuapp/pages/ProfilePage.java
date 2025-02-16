package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//i[contains(text(),'Logout')]")
    WebElement logOutButton;

    public ProfilePage verifySuccessfulLogin() {
        Assert.assertTrue(logOutButton.isDisplayed());
        return this;
    }
}
