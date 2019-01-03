package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class GoogleMapTest extends AbstractTest {

    @Test
    public void testGoogleMap() throws Exception{
        driver.get(baseUrl);
        driver.findElement(By.id("showTripsUnloggedLink")).click();
        try {
            assertTrue(isElementPresent(By.className("tripElement")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section[2]/div/div/div[2]/div/div/div[1]/div/div[1]/h4/a")).click();
        try {
            assertTrue(isElementPresent(By.id("googleMap")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

}
