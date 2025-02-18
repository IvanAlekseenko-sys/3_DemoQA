package herokuapp.nested_frames;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.NestedFramesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NestedFramesTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getNestedFrames();
    }

    @Test
    public void iframePositiveTest() {
        new NestedFramesPage(app.driver, app.wait)
                .getListOfFrames();
    }

    @Test
    public void switchToIframeByIndexPositiveTest() {
        new NestedFramesPage(app.driver, app.wait)
                .switchToIframeByIndex(0)
                //.verifyIframeText("LEFT")
        ;
    }
}
