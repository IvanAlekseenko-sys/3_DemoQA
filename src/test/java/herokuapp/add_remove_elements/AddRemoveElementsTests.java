package herokuapp.add_remove_elements;

import herokuapp.core.TestBase;
import herokuapp.pages.AddRemovePage;
import herokuapp.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddRemoveElementsTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getAddRemoveElements();
    }

    @Test
    public void AddRemoveTest() {
        new AddRemovePage(app.driver, app.wait)
                .addElement()
                .verifyAddOfElement()
                .removeElement()
                .verifyRemoveOfElement()
        ;
    }
}
