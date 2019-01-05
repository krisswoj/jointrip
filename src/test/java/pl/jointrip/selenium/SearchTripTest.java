package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class SearchTripTest extends AbstractTest {

    @Test
    public void testSearchTrip() throws Exception {

        driver.get(baseUrl);
        login("admin@gmail.com", "qwe123");
        addNewTrip(tripName);
        actions.moveToElement(driver.findElement(By.id("adminLink"))).perform();
        driver.findElement(By.id("acceptationPanelLink")).click();
        driver.findElement(By.id(tripName)).click();
        driver.findElement(By.id("acceptLink")).click();
        actions.moveToElement(driver.findElement(By.id("tripsLink"))).perform();
        driver.findElement(By.id("showTripsLoggedLink")).click();
        driver.findElement(By.id("tripTitle")).click();
        driver.findElement(By.id("tripTitle")).clear();
        driver.findElement(By.id("tripTitle")).sendKeys(tripName);
        driver.findElement(By.name("Submit")).click();
        try {
            assertTrue(isElementPresent(By.id(tripName)));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
