package pl.jointrip.selenium.abstractTest;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractTest {
    protected WebDriver driver;
    protected Actions actions;
    protected String baseUrl;
    protected boolean acceptNextAlert = true;
    protected StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        baseUrl = "http://localhost:5000";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void login() throws Exception {
        driver.findElement(By.id("loginLink")).click();
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("email")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueTypeEmail(driver.findElement(By.id("email")),"type"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtuePresent(driver.findElement(By.id("password")),"required"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertTrue(isAttribtueTypePassword(driver.findElement(By.id("password")),"type"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("aaa@aaa.aa");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("aaa");
        driver.findElement(By.name("Submit")).click();
        try {
            assertTrue(isElementPresent(By.id("message")));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals("Email Lub Haslo Nieprawidlowe", driver.findElement(By.id("message")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("qwe123");
        driver.findElement(By.name("Submit")).click();
    }

    public boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {}
        return result;
    }

    public boolean isAttribtueTypeEmail(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value.equals("email")){
                result = true;
            }
        } catch (Exception e) {}
        return result;
    }

    public boolean isAttribtueTypePassword(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value.equals("password")){
                result = true;
            }
        } catch (Exception e) {}
        return result;
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String closeAlertAndGetItsText() {
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


