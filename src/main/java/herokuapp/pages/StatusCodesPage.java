package herokuapp.pages;

import herokuapp.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StatusCodesPage extends BasePage {
    public StatusCodesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;


    public StatusCodesPage checkAllURL() {
        System.out.println("🔗 Общее количество ссылок на странице: [" + allLinks.size() + "]");
        for (WebElement link : allLinks) {
            linkCounter++;
            String urlText = link.getText().trim(); //хранит текст ссылки
            String href = link.getAttribute("href");//хранит саму ссылку
            System.out.println("🔗 Link " + linkCounter + ": [" + (!urlText.isEmpty() ? urlText : (href != null && !href.isEmpty() ? href : "null")) + "], URL: [" + (href != null ? href : "null") + "]");
        }
        return this;
    }

    public StatusCodesPage checkBrokenLinks() {
        System.out.println("🔍 Общее количество ссылок на странице: [" + allLinks.size() + "]");
        for (WebElement link : allLinks) {
            String urlText = link.getText().trim(); //хранит текст ссылки
            String linkURL = link.getAttribute("href"); // Хранит саму ссылку
            verifyLink(urlText, linkURL);
        }
        assertAll();
        return this;
    }

}
