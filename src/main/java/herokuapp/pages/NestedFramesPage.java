package herokuapp.pages;

import demoqa.pages.FramesPage;
import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NestedFramesPage extends BasePage {
    public NestedFramesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    @FindBy(tagName = "frame")
    List<WebElement> iframes;

    public NestedFramesPage getListOfFrames() {
        int numberOfFrames = ((Long) js.executeScript("return window.length")).intValue();
        System.out.println(numberOfFrames);
        if (iframes == null || iframes.isEmpty()) {
            System.out.println("No iframe was found using @FindBy");
            return this;
        }
        //System.out.println("Number of iframes on the page are: [" + numberOfFrames + "]");
        System.out.println("Number of iframes on the page are: [" + iframes.size() + "]");
        return this;
    }

    public NestedFramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(name = "frame-left")
    WebElement frameLeft;

    public NestedFramesPage verifyIframeText(String text) {
        shouldHaveText(frameLeft, text, 5000);
        return this;
    }

}
