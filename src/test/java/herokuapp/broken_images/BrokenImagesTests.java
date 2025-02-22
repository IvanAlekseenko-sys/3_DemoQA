package herokuapp.broken_images;

import herokuapp.core.TestBase;
import herokuapp.pages.BrokenImagesPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.pages.HomePage.HOME_PAGE_URL;

public class BrokenImagesTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/broken_images");
    }

    @Test
    public void brokenImagesPositiveTest(){
        new BrokenImagesPage(app.driver,app.wait)
                .checkAllBrokenLinksImages();
    }

}
