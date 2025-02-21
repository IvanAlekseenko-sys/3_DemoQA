package herokuapp.disappearing_elements;

import herokuapp.core.TestBase;
import herokuapp.pages.DisappearingElementsPage;
import herokuapp.utils.RetryAnalyser;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.pages.HomePage.HOME_PAGE_URL;


public class DisappearingElementsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/disappearing_elements");
    }

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void disappearingElementsTestsPositiveTest() {
        new DisappearingElementsPage(app.driver, app.wait)
                .checkDisappearingElement("Gallery");
    }
}

