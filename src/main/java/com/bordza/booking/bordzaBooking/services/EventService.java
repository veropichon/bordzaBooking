package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.domain.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    public List<Event> courseToEvent(List<CourseEntity> courseList) {
        List<Event> eventList = new ArrayList<>();
        for(CourseEntity course : courseList) {
            if (course.getCrsPublished() && !course.getCrsVip() && !course.getCrsIndisp()) {
                if (course.getDiscipline() == null) {
                    Event event = new Event(course.getCrsFromDate(),course.getCrsToDate(),course.getCrsTitle(), "nan","blue", "light green", "black");
                    eventList.add(event);
                } else if (course.getDiscipline().getDisLabel().equals("Skateboard")) {
                    Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "nan", "green", "light green", "black");
                    eventList.add(event);
                } else if (course.getDiscipline().getDisLabel().equals("Longboard")) {
                    Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "nan", "light green", "light green", "black");
                    eventList.add(event);
                }
            } else {
                Event event = new Event(course.getCrsFromDate(),course.getCrsToDate(),"Indisponible", "nan","grey","black","black");
                eventList.add(event);
            }
        }

        return eventList;
    }
}
