package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import com.bordza.booking.bordzaBooking.services.CourseService;
import com.bordza.booking.bordzaBooking.utils.SomeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class ValidationController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DisciplineRepository disciplineRepository;

    @Autowired
    DurationRepository durationRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    CourseClientRepository courseClientRepository;

    @Autowired
    CourseService courseService;

    private static final Logger log = LoggerFactory.getLogger("log ValidationController");

    @RequestMapping("/adminWaiting")
    public String validation(Model model) {

       // CourseClientEntity booking = courseClientRepository.findById(1L).get();
        //model.addAttribute("modelCourseClient", booking);

        List<CourseClientEntity> courseClientsList = courseClientRepository.findAll();
       // List<CourseClientEntity> courseClientsList = courseClientRepository.findAllbybkequalsfalse();
        model.addAttribute("courseClientsList" , courseClientsList);

        List<ClientEntity> clientsList = clientRepository.findAll();
        model.addAttribute("clientsList", clientsList);
        log.info("Taille liste booking : " + courseClientsList.size());
        String message = "Validation en attente (" + courseClientsList.size() + ")";
        model.addAttribute("pageTitle", message);

        //model.addAttribute("pageTitle", "Validation en attente ' ");

        return "adminWaiting";
    }

    @RequestMapping("/adminSummary")
    public String summary(Model model) {

        CourseEntity course = courseRepository.findById(1L).get();
        model.addAttribute("modelCourse", course);

        List<CourseClientEntity> courseClientsList = courseClientRepository.findAll();
        model.addAttribute("courseClientsList" , courseClientsList);


        /*log.info("course.getCrsFromDate() : " + course.getCrsFromDate());
        log.info("bean FromTime : " + someBean.getFromTime());

        log.info("bean FromDateTime : " + someBean.getFromDateTime());
        */

        model.addAttribute("pageTitle", "Récapitulatif");

        return "adminSummary";
    }
}
