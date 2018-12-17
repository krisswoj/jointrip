package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterUserTest extends AbstractTest {

    @Test
    public void testRegisterUser() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.id("registrationLink")).click();
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("name")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("lastName")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("email")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueType(driver.findElement(By.id("email")),"type", "email"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("password")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueType(driver.findElement(By.id("password")),"type", "password"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

        driver.findElement(By.id("name")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Dariusz");
        driver.findElement(By.id("lastName")).click();
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys("Tester");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("qwe123");
        driver.findElement(By.name("Submit")).click();
        try {
            assertTrue(isElementPresent(By.className("validation-message")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Uzytkownik o podanym emailu juz istnieje", driver.findElement(By.className("validation-message")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("user" + generator.nextInt(99999) + "@gmail.com");
        driver.findElement(By.name("Submit")).click();
        try {
            assertTrue(isElementPresent(By.id("message")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Rejestracja przebiegla pomyslnie Zaloguj", driver.findElement(By.id("message")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
