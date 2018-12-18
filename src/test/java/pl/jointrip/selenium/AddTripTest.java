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
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripTitle")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttributeMaxEqualToExpected(driver.findElement(By.id("tripTitle")),255, "maxlength"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripShortDesc")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttributeMaxEqualToExpected(driver.findElement(By.id("tripShortDesc")),255, "maxlength"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripFullDesc")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttributeMaxEqualToExpected(driver.findElement(By.id("tripFullDesc")),255, "maxlength"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripMembersAmount")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttributeMaxEqualToExpected(driver.findElement(By.id("tripMembersAmount")),50, "max"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripPriceMember")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripStartDate")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueType(driver.findElement(By.id("tripStartDate")),"type", "date"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripEndDate")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueType(driver.findElement(By.id("tripEndDate")),"type", "date"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("tripCountry")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttributeMaxEqualToExpected(driver.findElement(By.id("tripCountry")),255, "maxlength"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttributeMaxEqualToExpected(driver.findElement(By.id("tripCity")),255, "maxlength"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttributeMaxEqualToExpected(driver.findElement(By.id("tripStreet")),255, "maxlength"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("organizatorPhoneNumber")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueType(driver.findElement(By.id("organizatorPhoneNumber")),"type", "tel"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueType(driver.findElement(By.id("organizatorPhoneNumber")),"pattern", "[0-9]{9}"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("organizatorEmail")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueType(driver.findElement(By.id("organizatorEmail")),"type", "email"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
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


}
