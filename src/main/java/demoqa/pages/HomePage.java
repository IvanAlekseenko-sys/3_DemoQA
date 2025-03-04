package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //* BookStore
    @FindBy(css = ".top-card:nth-child(6)")
    WebElement bookStore;

    public SidePanel getBookStore() {
        scrollTo(300);
        click(bookStore);
        //clickWithJS(bookStore, 0,200);
        return new SidePanel(driver, wait);
    }

    //* AlertsFrameWindows
    @FindBy(css = ".top-card:nth-child(3)")
    WebElement alertsFrameWindows;

    public BasePage getAlertFrameWindows() {
        clickWithJS(alertsFrameWindows, 0, 200);
        return new SidePanel(driver, wait);
    }


    //* Widgets
    @FindBy(css = ".top-card:nth-child(4)")
    WebElement widgets;

    public BasePage getWidgets() {
        clickWithJS(widgets, 0, 200);
        return new SidePanel(driver, wait);
    }


    @FindBy(css = ".top-card:nth-child(1)")
    WebElement elements;

    public BasePage getElements() {
        click(elements);
        return new SidePanel(driver, wait);
    }

    @FindBy(css = ".top-card:nth-child(2)")
    WebElement forms;

    public BasePage getForms() {
        click(forms);
        return new SidePanel(driver, wait);
    }
}
