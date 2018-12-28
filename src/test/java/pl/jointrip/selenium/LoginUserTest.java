package pl.jointrip.selenium;

import org.junit.Test;
import pl.jointrip.selenium.abstractTest.AbstractTest;

public class LoginUserTest extends AbstractTest {

    @Test
    public void testLoginUser() throws Exception {

        driver.get(baseUrl);
        login();

    }

}