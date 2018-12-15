package pl.jointrip.selenium.abstractTest;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("qwe123");
        driver.findElement(By.name("Submit")).click();
    }

}


