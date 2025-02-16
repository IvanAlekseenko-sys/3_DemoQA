package demoqa.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import com.google.common.io.Files;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;


    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void typeWithJS(WebElement element, String text, int x, int y) {
        if (text != null) {
            js.executeScript("window.scrollBy(" + x + "," + y + ")");
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    public void clickWithJS(WebElement element, int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        click(element);
    }

    public void hideAds() {
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public String takeScreenshot() {
        System.out.println("🟢 Method takeScreenshot() called!");
        // Check for alert before taking the screenshot
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Timeout for alert detection
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();  // or alert.dismiss(); based on your use case
            System.out.println("Alert was present and accepted.");
        } catch (NoAlertPresentException e) {
            // No alert, proceed with screenshot capture
        } catch (TimeoutException e) {
            // Обработка TimeoutException, если alert так и не появился
            System.out.println("No alert present within timeout, proceeding to take screenshot.");
        }
        // Capture screenshot
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
        return screenshot.getAbsolutePath();
    }
}
