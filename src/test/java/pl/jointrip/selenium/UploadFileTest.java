package pl.jointrip.selenium;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UploadFileTest extends AbstractTest {

    @Ignore
    @Test
    public void testUploadFile() throws Exception {
        driver.get(baseUrl);
        login();
        actions.moveToElement(driver.findElement(By.id("userLink"))).perform();
        driver.findElement(By.id("docsApprovalLink")).click();
        driver.findElement(By.id("file")).click();
        driver.findElement(By.id("file")).clear();
        String filePath = System.getProperty("user.dir") + "/TestFileForSeleniumTest";
        driver.findElement(By.id("file")).sendKeys(filePath);
        driver.findElement(By.id("dropOperator")).click();
        new Select(driver.findElement(By.id("dropOperator"))).selectByVisibleText("Dowód osobisty");
        driver.findElement(By.name("Submit")).click();
        try {
            assertTrue(isElementPresent(By.id("messageSuccess")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Dodano nowy dokument", driver.findElement(By.id("messageSuccess")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

}
