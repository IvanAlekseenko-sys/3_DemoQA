package demoqa.widgets;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.SidePanel;
import demoqa.pages.WidgetsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectMenuTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getWidgets().hideAds();
        new SidePanel(app.driver, app.wait).selectSelectMenu().hideAds();
    }

    @Test
    public void selectOldStyleMenuPositiveTest() {
        new WidgetsPage(app.driver, app.wait)
                .selectOldStyleMenu("Indigo")
        ;
    }

}
