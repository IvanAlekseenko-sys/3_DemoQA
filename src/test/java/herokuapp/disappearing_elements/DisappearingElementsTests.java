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

    @Test
    public void  checkStyleButtonPositiveTest(){
        String button = "Home";
        new DisappearingElementsPage(app.driver, app.wait)
                .checkStyleButton(button, "#DA4B4B", "18px", "#ececec")
                .hoverMouseOnButton(button)
                .checkStyleButton(button, "#000000", "20px", "#f2f2f2")
        ;
    }

}

