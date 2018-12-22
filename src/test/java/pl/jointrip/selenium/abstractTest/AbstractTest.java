package pl.jointrip.selenium.abstractTest;

import java.util.Random;
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
    protected Random generator;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        baseUrl = "http://localhost:5000";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        generator = new Random();
    }

    public void login() throws Exception {
        driver.findElement(By.id("loginLink")).click();
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

    public void login(String email, String password) throws Exception {
        driver.findElement(By.id("loginLink")).click();
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
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("Submit")).click();
    }

    public void addNewTrip() throws Exception {
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

    public void addNewTrip(String tripTitle) throws Exception {
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
        driver.findElement(By.id("tripTitle")).sendKeys(tripTitle);
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

    public boolean isAttribtueType(WebElement element, String attribute, String type) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value.equals(type)){
                result = true;
            }
        } catch (Exception e) {}
        return result;
    }

    public boolean isAttributeMaxEqualToExpected(WebElement element, int expectedValue, String attribute){
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value.equals(String.valueOf(expectedValue))){
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


