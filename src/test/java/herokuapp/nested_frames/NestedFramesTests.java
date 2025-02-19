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
                .switchToIframeByIndex(1)
                .verifyIframeText("BOTTOM")
        ;
    }

    @Test
    public void switchToIframeByNamePositiveTest() {
        new NestedFramesPage(app.driver, app.wait)
                .switchToIframeByNamePositiveTest("frame-bottom")
                .verifyIframeText("BOTTOM")
        ;
    }

    @Test
    public void switchToLeftIframeByNamePositiveTest() {
        new NestedFramesPage(app.driver, app.wait)
                .switchToIframeByNamePositiveTest("frame-top")
                .switchToIframeByNamePositiveTest("frame-left")
                .verifyIframeText("LEFT")
                .stepUp()
                .switchToIframeByNamePositiveTest("frame-middle")
                .verifyIframeText("MIDDLE")
                .stepUp()
                .switchToIframeByNamePositiveTest("frame-right")
                .verifyIframeText("RIGHT")
                .exitFromAllFrames()
                .switchToIframeByNamePositiveTest("frame-bottom")
                .verifyIframeText("BOTTOM")



        ;
    }
}
