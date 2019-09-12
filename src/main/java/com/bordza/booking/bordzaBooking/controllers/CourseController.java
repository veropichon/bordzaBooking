package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.config.ConfigTemplateEngine;
import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import com.bordza.booking.bordzaBooking.services.CourseService;
import com.bordza.booking.bordzaBooking.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    ConfigTemplateEngine configTemplateEngine;

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


    private static final Logger log = LoggerFactory.getLogger("log CourseController");

    @RequestMapping("/newCourse")
    public String newCourse(Model model, @RequestParam String start) {

        model.addAttribute("modelCourseClient", new CourseClientEntity());

        List<LevelEntity> levelsList = levelRepository.findAll();
        model.addAttribute("modelLevelsList", levelsList);

        List<DurationEntity> durationsList = durationRepository.findAll();
        model.addAttribute("modelDurationsList", durationsList);

        List<DisciplineEntity> disciplinesList = disciplineRepository.findAll();
        model.addAttribute("modelDisciplinesList", disciplinesList);

        List<LocationEntity> locationsList = locationRepository.findAll();
        model.addAttribute("modelLocationsList", locationsList);

        model.addAttribute("modelLocation", new LocationEntity());
        model.addAttribute("modelDiscipline", new DisciplineEntity());

        CourseEntity course = new CourseEntity();
        LocalDateTime fromDate = LocalDateTime.parse(start);
        course.setCrsFromDate(fromDate);

        LocalDateTime toDate = fromDate.plusHours(1); // Par défaut : durée = 1 heure
        course.setCrsToDate(toDate);

        course.setCrsTitle("Titre par défaut"); // TODO : déterminer le titre par défaut
        course.setCrsDesc("Description par défaut"); // TODO : déterminer la description par défaut
        course.setCrsVip(false);
        model.addAttribute("modelCourse", course);

        // TODO récupérer l'ID client courant (cookie)
        // Pour l'instant : on suppose que le client est connecté et que c'est le client avec cliId = 1
        ClientEntity client = clientRepository.findById(1L).get();
        model.addAttribute("modelClient", client);

        // OK ** log.info("Date course.getCrsFromDate() : " + course.getCrsFromDate());

        return "newCourse";
    }


    // Save Course and Booking
    @PostMapping("/newCourse")
    public String saveCourseAndBooking(@ModelAttribute("modelCourse") CourseEntity courseEntity,
                                       @ModelAttribute("modelClient") ClientEntity clientEntity,
                                       @ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                                       BindingResult result, ModelMap model) {

        /*if (result.hasErrors()) {
            return "error";
        }*/

        courseEntity.defaultValue(courseEntity);
        courseClientEntity.defaultValue(courseClientEntity);

        try {

            log.info("Date course.getCrsFromDate() : " + courseEntity.getCrsFromDate());
            log.info("Date course.getCrsToDate() : " + courseEntity.getCrsToDate());

            courseService.saveCourse(courseEntity, clientEntity, courseClientEntity);
        } catch (IllegalArgumentException e) {


            return "newCourse";
        }
        return "redirect:/calendar";
    }
    // Récapitulatif du cours créé

    @RequestMapping("/courseSummary")
    public String courseSummary(Model model) {
        Long bookingId = (1L);
        CourseClientEntity booking = courseClientRepository.findById(bookingId).get();

        // log.info("id cours : " + booking.getCourse().getCrsFromDate());
        model.addAttribute("modelCourseClient", booking);

        model.addAttribute("pageTitle", "Récapitulatif");
        return "courseSummary";
    }

    // reservation d'un cours
    @RequestMapping("/reservation")
    public String reservation(Model model) {

        CourseEntity course = courseRepository.findById(1L).get();
        model.addAttribute("modelCourse", course);

        model.addAttribute("modelCourseClient", new CourseClientEntity());

        List<LocationEntity> locationsList = locationRepository.findAll();
        model.addAttribute("modelLocationsList", locationsList);

        model.addAttribute("modelLocation", new LocationEntity());
        model.addAttribute("modelDiscipline", new DisciplineEntity());
        model.addAttribute("pageTitle", "Réservation d'un cours");

        return "reservation";
    }

}
