package pl.jointrip.selenium;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import pl.jointrip.selenium.abstractTest.AbstractTest;

public class AddTripTest extends AbstractTest{

    @Test
    public void testAddTripProcess() throws Exception {
        driver.get(baseUrl);
        login();
        actions.moveToElement(driver.findElement(By.id("organiserLink"))).perform();
        driver.findElement(By.id("addTripLink")).click();
        driver.findElement(By.id("tripTitle")).click();
        driver.findElement(By.id("tripTitle")).clear();
        driver.findElement(By.id("tripTitle")).sendKeys("Wyjazd do Norwegii");
        driver.findElement(By.id("tripShortDesc")).click();
        driver.findElement(By.id("tripShortDesc")).clear();
        driver.findElement(By.id("tripShortDesc")).sendKeys("Zwiedzanie Oslo");
        driver.findElement(By.id("tripFullDesc")).click();
        driver.findElement(By.id("tripFullDesc")).clear();
        driver.findElement(By.id("tripFullDesc")).sendKeys("Wyjazd autokarem do Norwegii do Oslo");
        driver.findElement(By.id("tripMembersAmount")).click();
        driver.findElement(By.id("tripMembersAmount")).clear();
        driver.findElement(By.id("tripMembersAmount")).sendKeys("5");
        driver.findElement(By.id("tripPriceMember")).click();
        driver.findElement(By.id("tripPriceMember")).clear();
        driver.findElement(By.id("tripPriceMember")).sendKeys("500");
        driver.findElement(By.id("tripStartDate")).click();
        driver.findElement(By.id("tripStartDate")).clear();
        driver.findElement(By.id("tripStartDate")).sendKeys("2019-02-23");
        driver.findElement(By.id("tripEndDate")).click();
        driver.findElement(By.id("tripEndDate")).clear();
        driver.findElement(By.id("tripEndDate")).sendKeys("2019-02-28");
        driver.findElement(By.id("tripCountry")).click();
        driver.findElement(By.id("tripCountry")).clear();
        driver.findElement(By.id("tripCountry")).sendKeys("Norwegia");
        driver.findElement(By.id("tripCity")).click();
        driver.findElement(By.id("tripCity")).clear();
        driver.findElement(By.id("tripCity")).sendKeys("Oslo");
        driver.findElement(By.id("organizatorPhoneNumber")).click();
        driver.findElement(By.id("organizatorPhoneNumber")).clear();
        driver.findElement(By.id("organizatorPhoneNumber")).sendKeys("798564185");
        driver.findElement(By.id("organizatorEmail")).click();
        driver.findElement(By.id("organizatorEmail")).clear();
        driver.findElement(By.id("organizatorEmail")).sendKeys("przyklad@gmail.com");
        driver.findElement(By.name("Submit")).click();
        try {
            assertTrue(isElementPresent(By.id("message")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Wycieczka zostala dodana pomyslnie", driver.findElement(By.id("message")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}

