package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertTrue;

public class GalleryTest extends AbstractTest {

    @Test
    public void testAddPhoto() throws Exception {
        driver.get(baseUrl);
        login();
        actions.moveToElement(driver.findElement(By.id("organiserLink"))).perform();
        driver.findElement(By.id("tripsManagementLink")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div/div/div/div[2]/div/div/table/tbody/tr/td[2]/ul/li/h4/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div[2]/div/div/div[2]/div/a[8]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/section/div/div/div[1]/div/ul/li[2]/a")).click();
        driver.findElement(By.id("name-plan")).click();
        driver.findElement(By.id("name-plan")).clear();
        driver.findElement(By.id("name-plan")).sendKeys("Majorka");
        driver.findElement(By.id("file")).click();
        driver.findElement(By.id("file")).clear();
        String filePath = System.getProperty("user.dir") + "/TestPhotoForSeleniumTest.jpg";
        driver.findElement(By.id("file")).sendKeys(filePath);
        driver.findElement(By.name("Submit")).click();

    }

}
