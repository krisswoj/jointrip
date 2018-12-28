package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserManagementTest extends AbstractTest{

    @Test
    public void testBlockUser() throws Exception {

        driver.get(baseUrl);
        login();

        actions.moveToElement(driver.findElement(By.id("adminLink"))).perform();
        driver.findElement(By.id("adminUserManagementLink")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div/div/div[3]/table/tbody/tr/td[2]/ul/li/a[1]")).click();
        try {
            assertTrue(isElementPresent(By.id("messageSuccess")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Udalo sie zablokowac uzytkownika", driver.findElement(By.id("messageSuccess")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }

    }

}
