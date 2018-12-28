package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class AdminStatisticsElementExists extends AbstractTest {

    @Test
    public void testAdminStatisticsElementExists() throws Exception {

        driver.get(baseUrl);
        login();

        actions.moveToElement(driver.findElement(By.id("adminLink"))).perform();
        driver.findElement(By.id("statisticsLink")).click();
        try {
            assertTrue(isElementPresent(By.id("activeUsers")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
