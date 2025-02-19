package herokuapp.pages;

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
        System.out.println("Number of iframes on the page are: [" + numberOfFrames + "]");
        System.out.println("Number of iframes on the page are: [" + iframes.size() + "]");

        for (WebElement iframe : iframes) {
            String iframeID = iframe.getAttribute("id");
            String iframeSRC = iframe.getAttribute("src");
            System.out.println("Iframe found ID: [" + (iframeID != null ? iframeID : "No ID") + "], SRC: [" + (iframeSRC != null ? iframeSRC : "No SRC") + "]");

            System.out.println(iframeID);
            System.out.println(iframeSRC);
        }
        return this;
    }


    public NestedFramesPage switchToIframeByIndex(int index) {
        driver.switchTo().frame(index);
        return this;
    }

    @FindBy(xpath = "/html/body")
    WebElement frameBottom;

    public NestedFramesPage verifyIframeText(String text) {
        shouldHaveText(frameBottom, text, 5000);
        return this;
    }

    public NestedFramesPage switchToIframeByNamePositiveTest(String name) {
        driver.switchTo().frame(name);
        return this;
    }

    public NestedFramesPage stepUp() {
        driver.switchTo().parentFrame();
        return this;
    }

    public NestedFramesPage exitFromAllFrames() {
        driver.switchTo().defaultContent();
        return this;
    }
}

