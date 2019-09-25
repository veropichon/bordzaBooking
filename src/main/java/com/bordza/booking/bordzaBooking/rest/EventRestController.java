package com.bordza.booking.bordzaBooking.rest;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.domain.Event;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;
import com.bordza.booking.bordzaBooking.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
public class EventRestController {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EventService service;


    @RequestMapping("/courses")
    public List<Event> getCourses(@RequestParam(value = "start", required = true) String start,
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

        List<CourseEntity> courseList = courseRepository.findByCrsFromDateGreaterThanEqualAndCrsToDateLessThanEqual(startDateTime, endDateTime);
        return service.courseToEvent(courseList);

    }
}


@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadDateFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadDateFormatException(String dateString) {
        super(dateString);
    }
}
