package pl.jointrip.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import pl.jointrip.selenium.abstractTest.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JoinToTripTest extends AbstractTest {

    @Test
    public void testJoinToTrip() throws Exception {

        joinToTrip();

    }

}
