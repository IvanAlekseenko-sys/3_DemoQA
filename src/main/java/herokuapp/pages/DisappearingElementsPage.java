package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class DisappearingElementsPage extends BasePage {
    public DisappearingElementsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public DisappearingElementsPage checkDisappearingElement(String elementToFind) {
        try {
            WebElement element = driver.findElement(By.xpath("//a[normalize-space(text())='" + elementToFind + "']"));
            isElementPresent(element);
        } catch (Exception e) {
            throw new NoSuchElementException("No element found");
        }
        return this;
    }

    public boolean checkDisappearingElementWORetryAnalyser(String elementToFind) {
        By locator = By.xpath("//a[normalize-space(text())='" + elementToFind + "']");
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            System.out.println("Элемент '" + elementToFind + "' отсутствует.");
            return false;
        } else {
            wait.until(ExpectedConditions.visibilityOf(elements.get(0)));
            System.out.println("Элемент '" + elementToFind + "' присутствует.");
            return elements.get(0).isDisplayed();
        }
    }

}
