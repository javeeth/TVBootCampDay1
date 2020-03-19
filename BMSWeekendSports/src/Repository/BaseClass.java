package Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BaseClass {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        login(driver);
        getPrice(driver);
    }

    public static void login(WebDriver driver) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://in.bookmyshow.com/bengaluru/sports?dayGroup=Weekend&priceGroup=min0max500");
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public static void getPrice(WebDriver driver) throws InterruptedException {
        Thread.sleep(10000);
        WebElement price = driver.findElement(By.xpath("//div[@class= 'bd fd fe']"));
    }


}
