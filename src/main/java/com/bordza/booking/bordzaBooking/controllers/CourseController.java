package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CourseController {

    private static final Logger log = LoggerFactory.getLogger("test Course");

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

    // Send level, location, discipline, course model to "courseNew"
    @GetMapping("/courseNew")
    public String courseNew(Model model) {

        List<LevelEntity> levelsList = levelRepository.findAll();
        model.addAttribute("modelLevel", levelsList);

        model.addAttribute("modelLocation", new LocationEntity());
        model.addAttribute("modelDiscipline", new DisciplineEntity());
        model.addAttribute("modelCourse", new CourseEntity());

        return "courseNew";
    }
}
