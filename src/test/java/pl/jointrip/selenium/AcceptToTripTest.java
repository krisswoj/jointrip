package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AcceptToTripTest extends AbstractTest {

    @Test
    public void testAcceptUserToTrip() throws Exception{

        joinToTrip();

        driver.findElement(By.id("logoutLink")).click();
        login("admin@gmail.com", "qwe123");
        actions.moveToElement(driver.findElement(By.id("organiserLink"))).perform();
        driver.findElement(By.id("tripsManagementLink")).click();
        driver.findElement(By.linkText(tripName)).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div[2]/div/div/div[2]/div/a[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div[1]/div/div/form/div[1]/table/tbody/tr[2]/td[2]/div/div/a[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div[1]/div/div/form/div[1]/table/tbody/tr[2]/td[2]/div/div/ul/li[2]/a")).click();
        driver.findElement(By.name("Submit")).submit();
        try {
            assertTrue(isElementPresent(By.id("messageSuccess")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Udalo sie zmienic status uzytkownika", driver.findElement(By.id("messageSuccess")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }



}