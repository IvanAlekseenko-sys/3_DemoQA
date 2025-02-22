package herokuapp.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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

    protected static void scrollToEnd() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_END);
            robot.keyPress(KeyEvent.VK_END);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void click(WebElement element) {
        element.click();
    }

    public void clickWithJS(WebElement element, int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        click(element);
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public String takeScreenshot() {

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

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            Assert.assertTrue(isTextPresent, "Expected text: [" + text + "], actual text in element: [" + element.getText() + "] in element: [" + element + "]");
        } catch (TimeoutException e) {
            throw new AssertionError("Text not found in element: [" + element + "], was text:[" + element.getText() + "]", e);
        }

    }

    public void scrollTo(int y) {
        js.executeScript("window.scrollBy(0," + y + ")");

    }


    public boolean isElementPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            System.out.println("Элемент не найден по локатору: " + locator);
            return false;
        }
        try {
            // Если нужно дождаться видимости первого найденного элемента
            wait.until(ExpectedConditions.visibilityOf(elements.get(0)));
            System.out.println("Элемент найден: " + elements.get(0).getText() + ", видимость: " + elements.get(0).isDisplayed());
            return elements.get(0).isDisplayed();
        } catch (Exception e) {
            System.out.println("Элемент найден, но не прошёл ожидание видимости: " + e.getMessage());
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("Element " + element.getText() + " is present: " + element.isDisplayed());
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Element " + element.getText() + " is not present: " + e.getMessage());
            return false;
        }
    }

    //Переменная хранит счетчик ссылок
    public int linkCounter = 0;
    private SoftAssert softAssert = new SoftAssert();

    public void verifyLink(String urlText, String urlToCheck) {
        linkCounter++;
        try {
            URL url = new URL(urlToCheck);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                connection.setConnectTimeout(1000); //таймаут если ссылка битая
                connection.setReadTimeout(1000); // таймаут на чтение если сервер занят
                connection.setRequestMethod("GET");
                connection.connect(); // Открыть соединение

                int responseCode = connection.getResponseCode();
                String responseMessage = connection.getResponseMessage();
                // 100 - 399 успех
                // >= 400 - битая ссылка

                if (responseCode >= 400) {
                    System.err.println("❌ URL #" + linkCounter + ": [" + urlText + "], URL: [" + urlToCheck + "], response code: [" + responseCode + "], message: [" + responseMessage + "] -> Broken link");
                    softAssert.fail("❌ Broken link: #" + linkCounter + ": [" + urlText + "], URL: [" + urlToCheck + "] Code: [" + responseCode + "] Message: [" + responseMessage + "]");
                } else {
                    System.out.println("✅ URL #" + linkCounter + ": [" + urlText + "], URL: [" + urlToCheck + "], response code: [" + responseCode + "], message: [" + responseMessage + "] -> Valid link");
                }

            } finally {
                connection.disconnect(); // Закрыть соединение
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void assertAll() {
        softAssert.assertAll();
    }


    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void scrollToCenterPageWithJS() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
    }

    public void waitForPageScrollToFinish() {
        //* wait.until() всегда выполняется минимум два раза:
        //= Первый вызов при старте ожидания.
        //= Второй вызов для проверки условия выхода из do-while
        wait.until(driver -> { //это лямбда-выражение, которое принимает driver как аргумент и выполняет код внутри {}.
            double beforeScroll, afterScroll;
            int count = 0; // Инициализируем счётчик
            do {
                beforeScroll = ((Number) js.executeScript("return window.scrollY;")).doubleValue();
                System.out.println(beforeScroll);
                pause(500); // Ждём короткий промежуток времени
                afterScroll = ((Number) js.executeScript("return window.scrollY;")).doubleValue();
                System.out.println(afterScroll);
                System.out.println((++count) + "-я попытка подождать окончание скролла страницы.");
            } while (beforeScroll != afterScroll); // Если скролл ещё идёт, повторяем

            return true;
        });
    }
}
