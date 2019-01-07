package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class MyTripsTabsTest extends AbstractTest {

    @Test
    public void testMyTripsTabs() throws Exception {
        driver.get(baseUrl);
        login();
        actions.moveToElement(driver.findElement(By.id("tripsLink"))).perform();
        driver.findElement(By.id("myTripsLink")).click();
        try {
            assertTrue(isElementPresent(By.id("toVerifyTabLink")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isElementPresent(By.id("memberWaitForPaymentTabLink")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isElementPresent(By.id("paidMemberTabLink")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

}
