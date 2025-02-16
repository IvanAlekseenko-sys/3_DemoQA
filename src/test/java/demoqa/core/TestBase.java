package demoqa.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class TestBase {
    protected final ApplicationManager app = new ApplicationManager();
    private BasePage basePage;
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("🚀 Test STARTED: {}", method.getName());
        app.init();
        basePage = new BasePage(app.driver, app.wait);
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result) {
        try {
            if (!result.isSuccess()) {
                logger.error("⛔ Test FAILED: {}", method.getName());
                String screenshotPath = basePage.takeScreenshot();
                logger.error("📸 Screenshot: {}", screenshotPath);
            }
        } catch (Exception e) {
            logger.error("🔥 Error in tearDown: ", e);
        } finally {
            app.stop();
            logger.info("🛑 Browser closed");
        }
    }
}
