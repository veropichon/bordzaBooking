package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.Event;
import com.bordza.booking.bordzaBooking.repositories.EventJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
class EventController {

    @Autowired
    private EventJpaRepository eventRepository;

    @RequestMapping(value="/allevents", method= RequestMethod.GET)
    public List<Event> allEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping(value="/event", method=RequestMethod.POST)
    public Event addEvent(@RequestBody Event event) {
        Event created = eventRepository.save(event);
        return created;
    }

    @RequestMapping(value="/event", method=RequestMethod.PATCH)
    public Event updateEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @RequestMapping(value="/event", method=RequestMethod.DELETE)
    public void removeEvent(@RequestBody Event event) {
        eventRepository.delete(event);
    }

    @RequestMapping(value="/events", method=RequestMethod.GET)
    public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start,
                                        @RequestParam(value = "end", required = true) String end) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");

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

        // return eventRepository.findByDateBetween(startDateTime, endDateTime);
        return eventRepository.findByStartGreaterThanEqualAndFinishLessThanEqual(startDateTime, endDateTime);

    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadDateFormatException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadDateFormatException(String dateString) {
        super(dateString);
    }
}
