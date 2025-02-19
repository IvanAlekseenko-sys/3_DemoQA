package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HoversPage extends BasePage {
    public HoversPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "(//img[@alt='User Avatar'])[1]")
    WebElement user;

    public HoversPage makeHoverAppear() {
        Actions actions = new Actions(driver);
        actions.moveToElement(user).perform();
        return this;
    }

    @FindBy(xpath = "//h5[.='name: user1']")
    WebElement user1;

    public HoversPage verifyHoverText(String text) {
        shouldHaveText(user1, text, 5000);
        return this;
    }
}
