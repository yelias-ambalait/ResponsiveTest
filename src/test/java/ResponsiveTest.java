import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponsiveTest {
    private WebDriver driver;
    private static final String SCREENSHOT_PATH = "src/test/screenshots/";

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testResponsiveDesign() throws InterruptedException {
        // Navigate to your webpage
        driver.get("E:/Yelias/Projects/responsiveness/index.html");

        // Test on desktop (default size)
        performResponsiveTest("Desktop", 1200, 800);
        Thread.sleep(2000);

        // Test on mobile (iPhone X size)
        performResponsiveTest("Mobile (iPhone X)", 375, 812);
        Thread.sleep(1000);
        // Test on tablet (iPad size)
        performResponsiveTest("Tablet (iPad)", 768, 1024);
        Thread.sleep(1000);

        performResponsiveTest("Customized1", 600, 960);
        Thread.sleep(1000);
        performResponsiveTest("Customized2", 375, 812);
        Thread.sleep(1000);
        performResponsiveTest("Customized3", 768, 1024);
        Thread.sleep(1000);
        performResponsiveTest("Customized4", 1920, 1080);
        Thread.sleep(1000);
        performResponsiveTest("Customized5", 600, 600);
        Thread.sleep(2000);
    }

    private void performResponsiveTest(String fileName, int width, int height) {
        // Set the viewport size
        driver.manage().window().setSize(new Dimension(width, height));

        TakesScreenshot takesScreenshot = (TakesScreenshot) this.driver;
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);

        // Save the screenshot with a timestamp
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        try {
            Files.copy(screenshot.toPath(), Paths.get(SCREENSHOT_PATH + fileName + "_" + timestamp + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}

