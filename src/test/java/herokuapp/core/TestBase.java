package herokuapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {
    protected final ApplicationManager app = new herokuapp.core.ApplicationManager();
    public BasePage basePage;
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Test is started: [" + method.getName() + "]");
        app.init();
        basePage = new BasePage(app.driver, app.wait);
    }

    @AfterMethod(enabled = true)
    public void tearDown(Method method, ITestResult result) {
        if (result.isSuccess()) {
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            logger.error("Test is FAILED: [" + method.getName() + basePage.takeScreenshot() + "]");
        }
        app.stop();
    }
}
