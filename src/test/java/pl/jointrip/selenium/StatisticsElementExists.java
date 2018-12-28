package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class StatisticsElementExists extends AbstractTest{

    @Test
    public void testStatisticsElementExists() throws Exception{

        driver.get(baseUrl);
        try {
            assertTrue(Integer.parseInt(driver.findElement(By.id("activeUsersCount")).getText()) > 0);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
