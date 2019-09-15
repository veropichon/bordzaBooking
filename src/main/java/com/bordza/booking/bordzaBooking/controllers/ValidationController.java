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

    @RequestMapping("/waitingForValidation")
    public String validation(Model model) {

        CourseClientEntity booking = courseClientRepository.findById(1L).get();
        model.addAttribute("modelCourseClient", booking);
       /* CourseClientEntity courseClientEntity = new CourseClientEntity();
        courseClientEntity.defaultValue(courseClientEntity);

        List<CourseClientEntity> courseClientsList = courseClientRepository.findAll();
        model.addAttribute("modelCourseClientList", courseClientsList);

        List<CourseEntity> courseList = courseRepository.findAll();
        model.addAttribute("modelCoursesList", courseList);

        */

        //log.info("liste des cours : " + courseList);
        //log.info("liste des bookings : " + coursesClientList);

        // log.info("Cours : " + courseEntity.toString());
         //log.info("Booking : " + booking.toString());


        //List<LevelEntity> levelsList = levelRepository.findAll();
        // model.addAttribute("modelLevelsList", levelsList);

        //List<DurationEntity> durationsList = durationRepository.findAll();
        //model.addAttribute("modelDurationsList", durationsList);

        //List<DisciplineEntity> disciplinesList = disciplineRepository.findAll();
        //model.addAttribute("modelDisciplinesList", disciplinesList);

        //List<LocationEntity> locationsList = locationRepository.findAll();
        //model.addAttribute("modelLocationsList", locationsList);

        //.addAttribute("modelLocation", new LocationEntity());
        //model.addAttribute("modelDiscipline", new DisciplineEntity());

        //CourseEntity course = new CourseEntity();
        //course.defaultValue(course);

        //LocalDateTime fromDate = LocalDateTime.parse(start);//course.setCrsFromDate(fromDate);
        //LocalDateTime toDate = fromDate.plusHours(1); // Par défaut : durée = 1 heure
        //course.setCrsToDate(toDate);
        //course.setCrsTitle("Titre par défaut");
        //course.setCrsDesc("Description par défaut");
        //LevelEntity level = levelRepository.findById(1L).get();
        //course.setLevel(level);

        //model.addAttribute("modelCourse", course);

        // TODO récupérer l'ID client courant (cookie)
        // Pour l'instant : on suppose que le client est connecté et que c'est le client avec cliId = 1
        //ClientEntity client = clientRepository.findById(1L).get();
        //model.addAttribute("modelClient", client);



        /*log.info("course.getCrsFromDate() : " + course.getCrsFromDate());
        log.info("bean FromTime : " + someBean.getFromTime());

        log.info("bean FromDateTime : " + someBean.getFromDateTime());
        */

        model.addAttribute("pageTitle", "Validation en attente");

        return "waitingForValidation";
    }


}
