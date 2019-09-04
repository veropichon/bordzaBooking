package com.bordza.booking.bordzaBooking.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class EventController {

    @GetMapping("/cours")
    @ResponseBody
    public String cours() {

        return "test";
    }

    @RequestMapping("/courses")
    public String getCourses(@RequestParam(value = "start", required = true) String start,
                             @RequestParam(value = "end", required = true) String end) {

        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = inputDateFormat.parse(start);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad start date: " + start);
        }

        try {
            endDate = inputDateFormat.parse(end);
        } catch (ParseException e) {
            throw new BadDateFormatException("bad end date: " + end);
        }

        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(),
                ZoneId.systemDefault());

        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(),
                ZoneId.systemDefault());

//        List<CourseEntity> tet = courseRepository.findByCrsFromDateGreaterThanEqualAndCrsToDateLessThanEqual(startDateTime, endDateTime);
//        return courseRepository.findByCrsFromDateGreaterThanEqualAndCrsToDateLessThanEqual(startDateTime, endDateTime);

        return "rere";
    }
}


@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadDateFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadDateFormatException(String dateString) {
        super(dateString);
    }
}
