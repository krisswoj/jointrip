package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class ShowTripsNonLoggedTest extends AbstractTest {

    @Test
    public void testTripsExistsNonLogged() throws Exception{

        driver.get(baseUrl);
        driver.findElement(By.id("showTripsUnloggedLink")).click();
        try {
            assertTrue(isElementPresent(By.className("tripElement")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
