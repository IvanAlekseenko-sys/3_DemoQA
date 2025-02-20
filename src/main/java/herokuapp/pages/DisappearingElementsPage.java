package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DisappearingElementsPage extends BasePage {
    public DisappearingElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public DisappearingElementsPage checkDisappearingElement(String elementToFind) {
        WebElement element = driver.findElement(By.xpath("//a[normalize-space(text())='"+elementToFind+"']"));
        isElementPresent(element);
        return this;
    }
}
