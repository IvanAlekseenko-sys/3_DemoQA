package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//a[contains(text(),'Form Authentication')]")
    WebElement authForm;

    public LoginPage getAuthForm() {
        clickWithJS(authForm, 0, 500);
        return new LoginPage(driver, wait);
    }

    @FindBy(xpath = "//a[contains(text(),'Nested Frames')]")
    WebElement nestedFrames;

    public NestedFramesPage getNestedFrames() {
        click(nestedFrames);
        return new NestedFramesPage(driver, wait);
    }

    @FindBy(xpath = "//a[contains(text(),'Multiple Windows')]")
    WebElement multipleWindows;
    public MultipleWindows getMultipleWindows() {
        clickWithJS(multipleWindows,0,500);
        return new MultipleWindows(driver,wait);
    }
}
