package herokuapp.drop_down;

import herokuapp.core.TestBase;
import herokuapp.pages.DropDownPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.pages.HomePage.HOME_PAGE_URL;

public class DropDownTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/dropdown");
    }

    @Test
    public void selectDropDownOptionByNamePositiveTest() {
        String option = "Option 1";
        new DropDownPage(app.driver, app.wait)
                .selectOptionByText(option)
                .verifySelectedOption(option)
        ;

    }

    @Test
    public void selectDropDownOptionByValuePositiveTest() {
        String option = "2";
        new DropDownPage(app.driver, app.wait)
                .selectOptionByValue(option)
                .verifySelectedOption(option)
        ;
    }

    @Test
    public void selectDropDownOptionByIndexPositiveTest() {
        int index = 1;
        String option = "Option " + index;
        new DropDownPage(app.driver, app.wait)
                .selectOptionByIndex(index)
                .verifySelectedOption(option)
        ;
    }

    @Test
    public void selectDropDownOptionByIndexDefaultPositiveTest() {
        new DropDownPage(app.driver, app.wait)
                //.selectOptionByIndex(index)
                .verifySelectedOption("Please select an option")
        ;
    }
}
