package herokuapp.horizontal_slider;

import herokuapp.core.TestBase;
import herokuapp.pages.HorizontalSliderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.pages.HomePage.HOME_PAGE_URL;

public class HorizontalSliderTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/horizontal_slider");
    }

    @Test
    public void horizontalSliderPositiveTests() {
        Float setSlider = 1.5F;
        new HorizontalSliderPage(app.driver, app.wait)
                .moveSlider(setSlider)
                //.verifySliderValue(setSlider)
                ;
    }
}
