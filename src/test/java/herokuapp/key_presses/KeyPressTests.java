package herokuapp.key_presses;

import herokuapp.core.TestBase;
import herokuapp.pages.HomePage;
import herokuapp.pages.KeyPressPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KeyPressTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getKeyPresses();
    }


    @Test
    public void keyPressPositiveTest() {
        String letter = "a";
        new KeyPressPage(app.driver, app.wait)
                .pressKey(letter)
                .verifyPressedKey(letter)
        ;
    }
}
