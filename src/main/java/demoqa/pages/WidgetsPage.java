package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WidgetsPage extends BasePage {
    public WidgetsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id="oldSelectMenu")
        WebElement oldSelectMenu;
    public WidgetsPage selectOldStyleMenu(String color) {
        Select select = new Select(oldSelectMenu);
        select.selectByVisibleText(color);
        return this;
    }
}
