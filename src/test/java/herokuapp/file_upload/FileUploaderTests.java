package herokuapp.file_upload;

import herokuapp.core.TestBase;
import herokuapp.pages.FileUploaderPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static herokuapp.pages.HomePage.HOME_PAGE_URL;

public class FileUploaderTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        app.driver.get(HOME_PAGE_URL + "/upload");
    }

    @Test
    public void fileUploaderPositiveTest() {
        new FileUploaderPage(app.driver, app.wait)
                .chooseFileByChooseFileButton("C://Users//Ivan//Downloads//unnamed2.png")
                .clickOnUploadButton()
                .verifyFileName("unnamed2.png")
        ;
    }

    @Test
    public void fileUploaderInBoxPositiveTest() {
        new FileUploaderPage(app.driver, app.wait)
                .chooseFileInBox("C:\\Users\\Ivan\\Downloads\\unnamed2.png")
                .verifyFileNameInBox("unnamed2.png")
        ;
    }
}
