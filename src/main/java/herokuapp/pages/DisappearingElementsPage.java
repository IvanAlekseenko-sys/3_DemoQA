package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

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

    public DisappearingElementsPage checkStyleButton(String button, String expectedFontColor, String expectedFontSize, String expectedButtonColor) {
        WebElement homeButton = driver.findElement(By.xpath("//a[.='" + button + "']"));        // Найдем стили до наведения мышью
        SoftAssert softAssert = new SoftAssert();
        String actualFontColor = convertRgbToHex(homeButton.getCssValue("color"));
        String actualFontSize = convertRgbToHex(homeButton.getCssValue("font-size"));
        String actualButtonColor = convertRgbToHex(homeButton.getCssValue("background-color"));
        //Распечатываем актуальные значения стилей
        System.out.println("Цвет текста: " + actualFontColor + "(ожидался: " + expectedFontColor + ")");
        System.out.println("Размер текста " + actualFontSize + "(ожидался: " + expectedFontSize + ")");
        System.out.println("Цвет кнопки " + actualButtonColor + "(ожидался: " + expectedButtonColor + ")");

        // Проверки
        softAssert.assertEquals(actualFontColor, expectedFontColor.toUpperCase(), "❌ Цвет шрифта не совпадает");
        softAssert.assertEquals(actualFontSize, expectedFontSize, "❌ Размер шрифта не совпадает");
        softAssert.assertEquals(actualButtonColor, expectedButtonColor.toUpperCase(), "❌ Цвет кнопки не совпадает");
        softAssert.assertAll();
        return this;
    }

    private String convertRgbToHex(String rgbValue) {
        // Пример входного значения: "rgba(218, 75, 75, 1)"
        if (rgbValue.startsWith("rgba") || rgbValue.startsWith("rgb")) {
            rgbValue = rgbValue.replace("rgba(", "").replace("rgb(", "").replace(")", "");
            String[] values = rgbValue.split(",");
            int r = Integer.parseInt(values[0].trim());
            int g = Integer.parseInt(values[1].trim());
            int b = Integer.parseInt(values[2].trim());
            return String.format("#%02X%02X%02X", r, g, b);
        }
        return rgbValue; // Если уже в hex-формате
    }

    public DisappearingElementsPage hoverMouseOnButton(String button) {
        WebElement element = driver.findElement(By.xpath("//a[.='" + button + "']"));        // Найдем стили до наведения мышью
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        System.out.println("Курcор успешно наведён на элемент: [" + element.getText() +"]");
        return this;
    }
}
