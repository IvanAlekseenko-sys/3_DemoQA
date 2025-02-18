package herokuapp.multiple_windows;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.MultipleWindows;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultipleWindowsTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getMultipleWindows();
    }

    @Test
    public void newTabPositiveTests() {
        new MultipleWindows(app.driver, app.wait)
                .switchToNewTab(1)
                .verifyTabTitle("New Window");

    }

    @AfterMethod
    public void postcondition() {
        app.driver.navigate().to("https://the-internet.herokuapp.com/");
        app.driver.quit();
    }
}
