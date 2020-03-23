package repository;

import models.SportsDetail;
import models.SportsDetailList;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.BMSEvent;
import pageObject.BasePage;
import utility.FileConstant;
import utility.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class BMSEventsTest extends BasePage {

    BMSEvent bmsEvent;

    @BeforeTest
    void initializeTest() {
        setUp(FileConstant.URL, FileConstant.TimeOut);
        bmsEvent = PageFactory.initElements(driver, BMSEvent.class);
    }

    @Test
    void validateBMSWeekendEvents() throws IOException {
        Utilities utilities = new Utilities();
        SportsDetailList eventsDetailList = bmsEvent.getEvents();
        InputStream input = new FileInputStream(
                (System.getProperty("user.dir"))
                        + "/src/resources/EventsData.json");
        SportsDetailList storedEventsDetailList = utilities.deSerialize(input, SportsDetailList.class);

        storedEventsDetailList.sportsDetailList.stream().forEach((i) -> {
            Assert.assertTrue(utilities.isWeekend(i.getDay(),i.getMonth()));
            Assert.assertTrue((i.getAmount() <= 500));
        });

        Assert.assertEquals(eventsDetailList.sportsDetailList.size(), storedEventsDetailList.sportsDetailList.size());

        for (SportsDetail sportsDetail : eventsDetailList.sportsDetailList
             ) {
            Optional<SportsDetail> sportsDetail1 = storedEventsDetailList.sportsDetailList.stream().filter(i -> i.getName().equals(sportsDetail.getName())).findAny();
            Assert.assertTrue(sportsDetail1.isPresent());
            Assert.assertEquals(sportsDetail1.get().getAmount(), sportsDetail.getAmount());
            Assert.assertEquals(sportsDetail1.get().getDay(), sportsDetail.getDay());
            Assert.assertEquals(sportsDetail1.get().getMonth(), sportsDetail.getMonth());
            Assert.assertEquals(sportsDetail1.get().getCategory(), sportsDetail.getCategory());
        }
    }

    @AfterTest
    // Test cleanup
    public void quitBrowser() {
       teardownTest();
    }
}