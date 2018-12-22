package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcceptAndRejectTrip extends AbstractTest{

    @Test
    public void testAcceptAndRejectTrip() throws Exception {

        driver.get(baseUrl);
        login();

        addNewTrip();
        addNewTrip();

        actions.moveToElement(driver.findElement(By.id("adminLink"))).perform();
        driver.findElement(By.id("acceptationPanelLink")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[4]/ul/li/a[1]")).click();
        try {
            assertTrue(isElementPresent(By.id("messageSuccess")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Udalo sie zaakceptowac wycieczke", driver.findElement(By.id("messageSuccess")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[4]/ul/li/a[2]")).click();
        try {
            assertTrue(isElementPresent(By.id("messageSuccess")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Udalo sie odrzucic wycieczke", driver.findElement(By.id("messageSuccess")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
