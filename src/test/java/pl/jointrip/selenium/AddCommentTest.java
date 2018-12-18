package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddCommentTest extends AbstractTest {

    @Test
    public void testAddComment() throws Exception {
        driver.get(baseUrl);
        login();
        actions.moveToElement(driver.findElement(By.id("tripsLink"))).perform();
        driver.findElement(By.id("showTripsLoggedLink")).click();
        try {
            assertTrue(isElementPresent(By.className("tripElement")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section[2]/div/div/div[2]/div/div/div[1]/div/div[1]/h4/a")).click();
        driver.findElement(By.id("userQuestion")).click();
        driver.findElement(By.id("userQuestion")).clear();
        driver.findElement(By.id("userQuestion")).sendKeys("Pytanie?");
        driver.findElement(By.name("Submit")).click();
        try {
            assertTrue(isElementPresent(By.id("messageSuccess")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Wiadomosc zostala wyslana do organizatora", driver.findElement(By.id("messageSuccess")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

}
