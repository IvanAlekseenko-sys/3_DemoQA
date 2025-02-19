package herokuapp.check_box;

import herokuapp.core.TestBase;
import herokuapp.pages.CheckBoxPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.pages.HomePage.HOME_PAGE_URL;

public class CheckBoxTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/checkboxes");
    }

    @Test
    public void printCheckboxPositiveTest() {
        new CheckBoxPage(app.driver, app.wait)
                .printCheckBox();
    }

    @Test
    public void selectCheckboxByTextPositiveTest() {
        String checkBoxName = "checkbox 1";
        new CheckBoxPage(app.driver, app.wait)
                .selectCheckBoxByText(checkBoxName)
                .verifyCheckBox(checkBoxName)
        ;
    }

    @Test
    public void selectCheckboxByTextWithActionsPositiveTest() {
        String checkBoxName = "checkbox 1";
        new CheckBoxPage(app.driver, app.wait)
                .selectCheckBoxByTextWithActions(checkBoxName)
                .verifyCheckBox(checkBoxName)
        ;
    }

    @Test
    public void selectCheckboxByTextWithRobotPositiveTest() {
        String checkBoxName = "checkbox 1";
        new CheckBoxPage(app.driver, app.wait)
                .selectCheckBoxByTextWithRobot(checkBoxName)
                .verifyCheckBox(checkBoxName)
        ;
    }

}
