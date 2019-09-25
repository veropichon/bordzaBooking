package com.bordza.booking.bordzaBooking.rest;

import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseRestController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CourseClientRepository courseClientRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DisciplineRepository disciplineRepository;

    @GetMapping("/api/allCourses")
    public List<CourseClientEntity> showAllbooking() {
        return courseClientRepository.findAll();
    }

    @GetMapping("/api/courses")
    public List<CourseEntity> showAllCourses() {
        return courseRepository.findAll();
    }

}
