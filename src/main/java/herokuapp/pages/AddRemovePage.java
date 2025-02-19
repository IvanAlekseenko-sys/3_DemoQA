package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddRemovePage extends BasePage {
    public AddRemovePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(xpath = "//button[.='Add Element']")
    WebElement buttonAddElement;

    public AddRemovePage addElement() {
        Actions actions = new Actions(driver);
        actions.click(buttonAddElement).perform();
        return this;
    }

    @FindBy(xpath = "//button[.='Delete']")
    WebElement deleteButton;

    public AddRemovePage verifyAddOfElement() {
        Assert.assertTrue(deleteButton.isDisplayed());
        return this;
    }

//    @FindBy(xpath = "//div[@id='elements']//button[1]")
//    WebElement buttonDelete;

    public AddRemovePage removeElement() {
        Actions actions = new Actions(driver);
        actions.click(deleteButton).perform();
        return this;
    }

    public AddRemovePage verifyRemoveOfElement() {
        isElementPresent(By.xpath("//button[.='Delete']"));
        return this;
    }
}

