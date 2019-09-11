package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.domain.Event;
import com.bordza.booking.bordzaBooking.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class EventService {

    @Autowired
    DisciplineRepository disciplineRepository;


    public List<Event> courseToEvent(List<CourseEntity> courseList) {
        List<Event> eventList = new ArrayList<>();
        for(CourseEntity course : courseList) {
            if (course.getCrsPublished()) {
                if (!course.getCrsVip() && !course.getCrsUnavailable()) {
                    if (course.getDiscipline().getDisId() == 1) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "nan", "#29828E", "light blue", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 2) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "nan", "#097C4D", "green", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 3) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "nan", "#36C098", "green", "black");
                        eventList.add(event);
                    }
                } else if (course.getCrsVip() || course.getCrsUnavailable()) {
                    Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), "nan", "Indisponible", "grey", "black", "black");
                    eventList.add(event);
                }
            }
        }

        return eventList;
    }
}
