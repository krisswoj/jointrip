package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TripManagementTest extends AbstractTest {

    @Test
    public void testBlockTrip() throws Exception {

        driver.get(baseUrl);
        login();

        actions.moveToElement(driver.findElement(By.id("adminLink"))).perform();
        driver.findElement(By.id("adminTripManagementLink")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div/div/div[2]/table/tbody/tr/td[4]/ul/li/a[1]")).click();
        try {
            assertTrue(isElementPresent(By.id("messageSuccess")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Udalo sie zablokowac wycieczke", driver.findElement(By.id("messageSuccess")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }


}
