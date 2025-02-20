package herokuapp.pages;

import demoqa.pages.BookStorePage;
import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDropPage extends BasePage {
    public DragAndDropPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "column-b")
    WebElement column_b;
    @FindBy(id = "column-a")
    WebElement column_a;

    public DragAndDropPage actionDragMe() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(column_a, column_b).perform();
        return this;
    }

    public DragAndDropPage verifyText(String textToCheck) {
        shouldHaveText(column_b,textToCheck.toUpperCase(),5000);
        return this;
    }
}
