package pageObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.SportsDetail;
import models.SportsDetailList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import repository.EventsComparator;
import utility.WebElements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    public SportsDetailList getEvents() throws IOException {

        List<SportsDetail> eventDetails = new ArrayList<SportsDetail>();

        List<WebElement> totalEvents = findElements(WebElements.totalEventXpath);
        List<WebElement> price = findElements(WebElements.eventPriceXpath);
        List<WebElement> day = findElements(WebElements.eventDayXpath);
        List<WebElement> month = findElements(WebElements.eventMonthXpath);
        List<WebElement> category = findElements(WebElements.eventCategoryXpath);
        List<WebElement> name = findElements(WebElements.eventNameXpath);

        ObjectMapper mapper = new ObjectMapper();

        if (totalEvents.size() > 0) {
            for (int i = 0; i < totalEvents.size(); i++) {
                SportsDetail sportsDetail = new SportsDetail();
                sportsDetail.setDay(Integer.parseInt(day.get(i).getText()));
                sportsDetail.setAmount(Integer.parseInt(price.get(i).getText().replace("Rs. ", "")));
                sportsDetail.setMonth(month.get(i).getText());
                sportsDetail.setCategory(category.get(i).getText());
                sportsDetail.setName(name.get(i).getText());

                eventDetails.add(sportsDetail);
            }
            Collections.sort(eventDetails, new EventsComparator());
        }

        SportsDetailList sportsDetailList = new SportsDetailList();
        sportsDetailList.sportsDetailList = eventDetails;

        mapper.writeValue(new File("src/resources/EventsData.json"), sportsDetailList);

        return sportsDetailList;
    }

}
