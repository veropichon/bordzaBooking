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
        for (CourseEntity course : courseList) {
            if (course.getCrsPublished()) {
                if (!course.getCrsVip()) {
                    if (course.getDiscipline().getDisId() == 1) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + "  :  " + course.getCourseClients().size() + "/12", "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "#29828E", "black", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 2) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + "  :  " + course.getCourseClients().size() + "/12", "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "#097C4D", "black", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 3) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + "  :  " + course.getCourseClients().size() + "/12", "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "#36C098", "black", "black");
                        eventList.add(event);
                    } else {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + "  :  " + course.getCourseClients().size() + "/12", "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "red", "black", "black");
                        eventList.add(event);
                    }
                } else {
                    if (course.getDiscipline().getDisId() == 1) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + " VIP", "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "#29828E", "black", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 2) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + " VIP", "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "#097C4D", "black", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 3) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + " VIP", "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "#36C098", "black", "black");
                        eventList.add(event);
                    } else {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle(), "/adminSummary?courseId=" + course.getCrsId() + "&ori=2", "red", "black", "black");
                        eventList.add(event);
                    }
                }
            }
        }

        return eventList;
    }
}
