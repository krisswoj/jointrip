package pl.jointrip.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
//@RunWith(SpringRunner.class)
public class SystemNotificationTest {

    @Test
    public void getSystemNotification() {
    }

    @Test
    public void setSystemNotification() {

        SystemNotification systemNotification = new SystemNotification("fdgfhg", "sfdgfhgj");
        systemNotification.getSystemNotification();

    }
}