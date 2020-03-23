package repository;

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
        SportsDetailList sportsDetailList = bmsEvent.getEvents();
        InputStream input = new FileInputStream(
                (System.getProperty("user.dir"))
                        + "/src/resources/EventsData.json");
        sportsDetailList = utilities.deSerialize(input, SportsDetailList.class);

        sportsDetailList.sportsDetailList.stream().forEach((i) ->{
            Assert.assertTrue(utilities.isWeekend(i.getDay(),i.getMonth()));
            Assert.assertTrue((i.getAmount() <= 500));
        });
    }

    @AfterTest
    // Test cleanup
    public void quitBrowser() {
       teardownTest();
    }
}