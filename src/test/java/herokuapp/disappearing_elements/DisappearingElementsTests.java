package herokuapp.disappearing_elements;

import herokuapp.core.TestBase;
import herokuapp.pages.DisappearingElementsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.pages.HomePage.HOME_PAGE_URL;


public class DisappearingElementsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/disappearing_elements");
    }

    @Test
    public void disappearingElementsTestsPositiveTest() {
        new DisappearingElementsPage(app.driver, app.wait)
                .checkDisappearingElement("Gallery");
    }
}

