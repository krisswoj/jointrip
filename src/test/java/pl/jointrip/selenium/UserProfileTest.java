package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class UserProfileTest extends AbstractTest{

    @Test
    public void testUserProfile() throws Exception {
        driver.get(baseUrl);
        login("admin@gmail.com", "qwe123");
        actions.moveToElement(driver.findElement(By.id("userLink"))).perform();
        driver.findElement(By.id("userInfoLink")).click();
        try {
            assertTrue(isElementPresent(By.id("changePassword")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }
}
