package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.CourseClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Controller
public class HeaderController {

    @Autowired
    CourseClientRepository courseClientRepository;

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping
    public String validatedCount(Model model){

        Map<String, Integer> bookingsAndCoursesToValidate = new HashMap<>();

        CourseEntity courseEntity = new CourseEntity();
        CourseClientEntity courseClientEntity = new CourseClientEntity();

        //Integer CourseToValid = courseRepository;
        Integer BookingToValid = courseClientRepository.findAllByBkValidated(true).size();
        log.info("BookingToValid : " + BookingToValid);

        //bookingsAndCoursesToValidate.put("modelCourseToValid",  );
        bookingsAndCoursesToValidate.put("modelBookingToValid", BookingToValid );

        model.addAllAttributes(bookingsAndCoursesToValidate);

        return "/general";
    }

}
