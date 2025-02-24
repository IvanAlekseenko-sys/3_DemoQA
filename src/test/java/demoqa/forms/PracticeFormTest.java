package demoqa.forms;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.PracticeFormPage;
import demoqa.pages.SidePanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PracticeFormTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getForms().hideAds();
        new SidePanel(app.driver, app.wait).selectPracticeFormMenu().hideAds();
    }

    @Test
    public void practiceFormPositiveTest() {
        new PracticeFormPage(app.driver, app.wait)
                .enterPersonalData("Alucard", "Tsepesh", "test@test.com", "1234567890")
                .selectGender("Male")
                //.chooseBirthDateAsString("10 Jan 1450")
                .chooseDate("10", "January", "1910")
                .enterSubjects(new String[]{"Maths", "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:\\Users\\Ivan\\Downloads\\unnamed2.png")
        // .enterCurrentAddress("Transylvania, Romania")
        //.enterState("NCR")
        //.enterCity("Bucharest")
        // .submitForm()
        // .verifySuccessRegistration("Thanks for submitting the form")
        ;

    }
}
