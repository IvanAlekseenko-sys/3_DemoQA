package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MultipleWindows extends BasePage {
    public MultipleWindows(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    @FindBy(xpath = "//a[contains(text(),'Click Here')]")
    WebElement newTabButton;

    public MultipleWindows switchToNewTab(int index) {
        click(newTabButton);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
        return this;
    }

    @FindBy(xpath = "//h3[contains(text(),'New Window')]")
    WebElement newWindow;

    public MultipleWindows verifyTabTitle(String text) {
        shouldHaveText(newWindow, text, 5000);
        return this;

    }
}
