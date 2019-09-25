package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.domain.Event;
import com.bordza.booking.bordzaBooking.repositories.CourseClientRepository;
import com.bordza.booking.bordzaBooking.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class EventService {

    @Autowired
    DisciplineRepository disciplineRepository;

    @Autowired
    CourseClientRepository courseClientRepository;


    public List<Event> courseToEvent(List<CourseEntity> courseList) {
        List<Event> eventList = new ArrayList<>();
        for (CourseEntity course : courseList) {
            if (course.getCrsPublished()) {
                if (!course.getCrsVip() && !course.getCrsUnavailable()) {
                    if (course.getDiscipline().getDisId() == 1) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + "  :  " + countParticipants(course) + "/12", "/reservation?courseId=" + course.getCrsId(), "#29828E", "black", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 2) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + "  :  " + countParticipants(course) + "/12", "/reservation?courseId=" + course.getCrsId(), "#097C4D", "black", "black");
                        eventList.add(event);
                    } else if (course.getDiscipline().getDisId() == 3) {
                        Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), course.getCrsTitle() + "  :  " + countParticipants(course) + "/12", "/reservation?courseId=" + course.getCrsId(), "#36C098", "black", "black");
                        eventList.add(event);
                    }
                } else if (course.getCrsVip() || course.getCrsUnavailable()) {
                    Event event = new Event(course.getCrsFromDate(), course.getCrsToDate(), "Indisponible", "none", "grey", "black", "black");
                    eventList.add(event);
                }
            }
        }

        return eventList;
    }

    private int countParticipants(CourseEntity course) {
        List<CourseClientEntity> li = courseClientRepository.findAllByCourseIsAndBkValidatedTrue(course);
        return li.size();
    }
}
