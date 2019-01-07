package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class TravelerPanelTest extends AbstractTest {

    @Test
    public void testAddFilesSection() throws Exception {
        driver.get(baseUrl);
        login();
        actions.moveToElement(driver.findElement(By.id("organiserLink"))).perform();
        driver.findElement(By.id("tripsManagementLink")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div/div/div/div[2]/div/div/table/tbody/tr/td[2]/ul/li/h4/a")).click();
        try {
            assertTrue(isElementPresent(By.id("tripManagement")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
