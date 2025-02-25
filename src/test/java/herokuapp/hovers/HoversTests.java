package herokuapp.hovers;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.HoversPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HoversTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getHovers();
    }

    @Test(groups = {"smoke"})
    public void hoverUserTextPositiveTest() {
        new HoversPage(app.driver, app.wait)
                .makeHoverAppear()
                .verifyHoverText("user1")
        ;

    }
}
