package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PracticeFormPage extends BasePage {
    public PracticeFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;

    public PracticeFormPage enterPersonalData(String name, String surname, String email, String telNumber) {
        type(firstName, name);
        type(lastName, surname);
        type(userEmail, email);
        type(userNumber, telNumber);
        System.out.printf("Personal data: [%s], [%s], [%s], [%s]%n", name, surname, email, telNumber);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        try {
            String xpathGender = "//label[.='" + gender + "']";
            WebElement genderLocator = driver.findElement(By.xpath(xpathGender));
            click(genderLocator);
            System.out.printf("✅ Selected gender: [%s]%n", gender);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("⛔ Gender element not found: [" + gender + "]" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Error selecting gender: [" + gender + "]. " + e);
        }


        return this;
    }

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    public PracticeFormPage chooseBirthDateAsString(String date) {
        //type(dateOfBirthInput, date);
        click(dateOfBirthInput);
        if (System.getProperty("os.name").contains("Mac")) {
            dateOfBirthInput.sendKeys(Keys.COMMAND, "a");
        } else {
            dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        }
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ Selected date: [%s]%n", date);
        return this;
    }

    @FindBy(className = "react-datepicker__month-select")
    WebElement monthSelect;
    @FindBy(className = "react-datepicker__year-select")
    WebElement yearSelect;

    public PracticeFormPage chooseDate(String day, String month, String year) {
        if (day == null || month == null || year == null) {
            throw new IllegalArgumentException("⛔ Введены некорректные данные");
        }
        click(dateOfBirthInput);
        Select selectMonth = new Select(monthSelect);
        selectMonth.selectByVisibleText(month);
        Select selectYear = new Select(yearSelect);
        selectYear.selectByVisibleText(year);
        String dayToChoose = "//div[contains(@class,'react-datepicker__day react-datepicker__day--0" + day + "')]";
        WebElement dayLocator = driver.findElement(By.xpath(dayToChoose));
        click(dayLocator);
        System.out.printf("✅ Selected date: [%s],[%s],[%s]%n", day, month, year);
        return this;
    }

    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    public PracticeFormPage enterSubjects(String[] subjects) {
        for (String subject : subjects) {
            type(subjectsInput, subject);
            subjectsInput.sendKeys(Keys.ENTER);
            System.out.printf("✅ Selected subject: [%s]%n", subject);
        }

        return this;
    }

    public PracticeFormPage chooseHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            By hobbyLocator = By.xpath("//label[.='" + hobby + "']");
            WebElement element = driver.findElement(hobbyLocator);
            click(element);
            System.out.printf("✅ Selected hobby: [%s]%n", hobby);
        }

        return this;
    }

    @FindBy(id = "uploadPicture")
    WebElement uploadPicture;

    public PracticeFormPage uploadPicture(String imgPath) {
        uploadPicture.sendKeys(imgPath);
        System.out.printf("✅ Image: [%s]%n", imgPath);
        String picturePath = uploadPicture.getAttribute("value");
        Assert.assertEquals("C:\\fakepath\\unnamed2.png", picturePath);
        return this;
    }


}
