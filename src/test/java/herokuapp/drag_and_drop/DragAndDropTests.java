package herokuapp.drag_and_drop;

import herokuapp.core.TestBase;
import herokuapp.pages.DragAndDropPage;
import herokuapp.pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getDragAndDrop();
    }

    @Test
    public void dragAndDropPositiveTest(){
        new DragAndDropPage(app.driver, app.wait)
                .actionDragMe()
                .verifyText("a")
        ;
    }
}
