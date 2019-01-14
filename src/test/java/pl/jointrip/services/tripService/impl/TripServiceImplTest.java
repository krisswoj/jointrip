package pl.jointrip.services.tripService.impl;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TripServiceImplTest {

    @Test
    public void daysAmountInTrip() throws ParseException {

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        String dateBeforeString = "2019-01-15 00:00:00.000000";
        String dateAfterString = "2019-01-25 00:00:00.000000";

        Date data1 = inputFormat.parse(dateAfterString);
        Date date2 = inputFormat.parse(dateBeforeString);

        LocalDate date11 = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date12 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }



}