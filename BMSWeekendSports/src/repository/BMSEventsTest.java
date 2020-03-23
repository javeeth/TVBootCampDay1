package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.SportsDetail;
import models.SportsDetailList;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import pageObject.BMSEvent;
import pageObject.BasePage;
import utility.FileConstant;
import utility.WebElements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BMSEventsTest extends BasePage {

    BMSEvent bmsEvent;

    @BeforeTest
    void initializeTest() {
        setUp(FileConstant.URL, FileConstant.TimeOut);
        bmsEvent = PageFactory.initElements(driver, BMSEvent.class);
    }

    @Test
    void getEventsDetail() throws IOException {

        List<SportsDetail> eventDetails = new ArrayList<SportsDetail>();
        List<WebElement> totalEvents = bmsEvent.findElements(WebElements.totalEventXpath);
        List<WebElement> price = bmsEvent.findElements(WebElements.eventPriceXpath);
        List<WebElement> day = bmsEvent.findElements(WebElements.eventDayXpath);
        List<WebElement> month = bmsEvent.findElements(WebElements.eventMonthXpath);
        List<WebElement> category = bmsEvent.findElements(WebElements.eventCategoryXpath);
        List<WebElement> name = bmsEvent.findElements(WebElements.eventNameXpath);

        ObjectMapper mapper = new ObjectMapper();

        if(totalEvents.size() > 0){
            for(int i = 0; i < totalEvents.size(); i++){
                SportsDetail sportsDetail = new SportsDetail();
                sportsDetail.setDay(day.get(i).getText());
                sportsDetail.setAmount(Integer.parseInt(price.get(i).getText().replace("Rs. ", "")));
                sportsDetail.setMonth(month.get(i).getText());
                sportsDetail.setCategory(category.get(i).getText());
                sportsDetail.setName(name.get(i).getText());

                eventDetails.add(sportsDetail);
            }
            Collections.sort(eventDetails,new EventsComparator());
        }

        SportsDetailList sportsDetailList = new SportsDetailList();
        sportsDetailList.sportsDetailList = eventDetails;

        mapper.writeValue(new File("src/resources/EventsData.json"), sportsDetailList);
        InputStream input = new FileInputStream(
                (System.getProperty("user.dir"))
                        + "/src/resources/EventsData.json");
        sportsDetailList = mapper.readValue(input,
                SportsDetailList.class);


    }

    @AfterTest
    // Test cleanup
    public void quitBrowser() {
       teardownTest();
    }
}
