package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver = null;

    public void setUp(String URL, int timoutSeconds)
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(timoutSeconds, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    // Test cleanup
    public void teardownTest() {
        BasePage.driver.quit();
    }

}
