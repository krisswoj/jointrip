package pl.jointrip.selenium;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import pl.jointrip.selenium.abstractTest.AbstractTest;

public class AddTripTest extends AbstractTest{

    @Test
    public void testAddTripProcess() throws Exception {
        driver.get(baseUrl);
        login();
        addNewTrip();
    }


}

