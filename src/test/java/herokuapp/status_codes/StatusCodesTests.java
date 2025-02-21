package herokuapp.status_codes;

import herokuapp.pages.StatusCodesPage;
import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StatusCodesTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getStatusCodes();
    }

    @Test
    public void statusCodePositiveTest(){
        new StatusCodesPage(app.driver, app.wait)
                .checkAllURL()
                .checkBrokenLinks()
        ;
    }
}
