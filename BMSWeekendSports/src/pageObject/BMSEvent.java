package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BMSEvent{

    public WebDriver driver;

    public BMSEvent(WebDriver driver){
        this.driver = driver;
    }

    public List<WebElement> findElements(String xPath){
        List<WebElement> webElements = driver.findElements(By.xpath(xPath));
        return webElements;
    }
}
