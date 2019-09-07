package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.config.ConfigTemplateEngine;
import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger("log CourseController");

    @Autowired
    ConfigTemplateEngine configTemplateEngine;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    DisciplineRepository disciplineRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    LocationRepository locationRepository;

    // @Autowired
    // CourseService courseService;

    // TODO Valérian -> passer la date sur laquelle on a cliqué dans le calendrier
    @GetMapping("/newCourse")
    public String newCourse(Model model) {

        List<LevelEntity> levelsList = levelRepository.findAll();
        model.addAttribute("modelLevelsList", levelsList);

        List<DisciplineEntity> disciplinesList = disciplineRepository.findAll();
        model.addAttribute("modelDisciplinesList", disciplinesList);

        List<LocationEntity> locationsList = locationRepository.findAll();
        model.addAttribute("modelLocationsList", locationsList);

        model.addAttribute("modelLocation", new LocationEntity());
        model.addAttribute("modelDiscipline", new DisciplineEntity());

        CourseEntity course = new CourseEntity();
        LocalDateTime dateTest = LocalDateTime.now();
        course.setCrsFromDate(dateTest);
        course.setCrsTitle("Titre par défaut"); // TODO : déterminer le titre par défaut
        model.addAttribute("modelCourse", course);

        // OK ** log.info("Date course.getCrsFromDate() : " + course.getCrsFromDate());

        return "newCourse";
    }
}
