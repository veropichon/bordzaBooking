package com.bordza.booking.bordzaBooking.rest;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;
import com.bordza.booking.bordzaBooking.repositories.LocationRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
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

    @GetMapping("/api/allCourses")
    public List<CourseEntity> showAllCourses(){

        return courseRepository.findAll();
    }



}
