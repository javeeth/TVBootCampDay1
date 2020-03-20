package Repository;

import Models.SportsDetail;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    static WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws InterruptedException, IOException {
        setDriverProperty();
        login();
        getDetails();
        closeDriver();
    }

    public static void setDriverProperty(){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
    }

    public static void login() throws InterruptedException {
        driver.get("https://in.bookmyshow.com/bengaluru/sports?dayGroup=Weekend&priceGroup=min0max500");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void getDetails() throws JsonMappingException, IOException {
        List<SportsDetail> eventDetails = new ArrayList<>();
        List<WebElement> totalEvents = driver.findElements(By.xpath("//div[@class = 'ax c es et eu ev k v']"));
        List<WebElement> price = driver.findElements(By.xpath("//div[contains(@class, 'bd fd fe')]"));
        List<WebElement> day = driver.findElements(By.xpath("//div[@class = 'bd ey']"));
        List<WebElement> month = driver.findElements(By.xpath("//div[@class = 'bd ch ez']"));
        List<WebElement> category = driver.findElements(By.xpath("//div[@class = 'ap bd fe']"));
        List<WebElement> name = driver.findElements(By.xpath("//div[@class = 'al b ch fb fc']"));

        for(int i = 0; i < totalEvents.size(); i++){
            SportsDetail sportsDetail = new SportsDetail();
            sportsDetail.set_day(day.get(i).getText());
            sportsDetail.set_amount(price.get(i).getText());
            sportsDetail.set_month(month.get(i).getText());
            sportsDetail.set_category(category.get(i).getText());
            sportsDetail.set_name(name.get(i).getText());

            eventDetails.add(sportsDetail);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("EventsData.json"), eventDetails);

    }

    public static void closeDriver(){
        driver.close();
    }

}
