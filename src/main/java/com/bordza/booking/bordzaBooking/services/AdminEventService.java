package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.domain.Event;
import com.bordza.booking.bordzaBooking.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminEventService {

    @Autowired
    DisciplineRepository disciplineRepository;


    public List<Event> adminCourseToEvent(List<CourseEntity> courseList) {
        List<Event> eventList = new ArrayList<>();
        for(CourseEntity course : courseList) {
            if (course.getCrsPublished()) {
                if (!course.getCrsVip()) {
                    if (course.getDiscipline().getDisId() == 1) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "/reservation?courseId=" + course.getCrsId(), "#29828E", "light blue", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 2) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "/reservation?courseId=" + course.getCrsId(), "#097C4D", "green", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 3) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "/reservation?courseId=" + course.getCrsId()   , "#36C098", "green", "black");
                        eventList.add(event);
                    } else {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "/reservation?courseId=" + course.getCrsId()   , "red", "green", "black");
                        eventList.add(event);
                    }
                } else {
                    if (course.getDiscipline().getDisId() == 1) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + " VIP", "/reservation?courseId=" + course.getCrsId(), "#29828E", "light blue", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 2) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + " VIP", "/reservation?courseId=" + course.getCrsId(), "#097C4D", "green", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 3) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + " VIP", "/reservation?courseId=" + course.getCrsId()   , "#36C098", "green", "black");
                        eventList.add(event);
                    } else {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "/reservation?courseId=" + course.getCrsId()   , "red", "green", "black");
                        eventList.add(event);
                    }
                }
            }
        }

        return eventList;
    }
}
