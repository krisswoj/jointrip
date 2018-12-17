package pl.jointrip.services.tripService.impl;

import org.junit.Test;
import pl.jointrip.models.entities.trip.Trip;
import sun.jvm.hotspot.utilities.Interval;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.Assert.*;

public class TripServiceImplTest {

    @Test
    public void daysAmountInTrip() throws ParseException {

//        String sDate1 = "2019-01-15";
//        String sDate2 = "2019-01-20";
//
//        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
//        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
////        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
//
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
////        LocalDate date11 = LocalDate.parse("03-29-2015", formatter);
////        LocalDate date22 = LocalDate.parse("03-30-2015", formatter);
//
//        LocalDate date11 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate date22 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        Trip trip = new Trip();
//        trip.setTripStartDate(date1);
//        trip.setTripEndDate(date2);

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        String dateBeforeString = "2019-01-15 00:00:00.000000";
        String dateAfterString = "2019-01-25 00:00:00.000000";

        Date data1 = inputFormat.parse(dateAfterString);
        Date date2 = inputFormat.parse(dateBeforeString);

        LocalDate date11 = data1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date12 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        //Parsing the date
//        LocalDate dateBefore = LocalDate.parse(data1);
//        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        System.out.println(ChronoUnit.DAYS.between(date12, date11));

//        Interval interval = new Interval(trip.getTripStartDate(), trip.getTripEndDate());
//        System.out.println(interval.toString());
    }
}