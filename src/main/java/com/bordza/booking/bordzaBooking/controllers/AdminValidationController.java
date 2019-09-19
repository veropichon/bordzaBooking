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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class AdminValidationController {

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

    @GetMapping("/adminWaiting")
    public String validation(Model model) {
        List<CourseClientEntity> courseClientsList = courseClientRepository.findAllByBkValidated(false);

     /*   if (courseClientsList.size() == 0) {
            String message = "Validation en attente ";
            String message1 = "Toutes vos validations sont effectuées";
            model.addAttribute("pageTitle", message);
            model.addAttribute("nbvalidation", courseClientsList.size());
            model.addAttribute("information", message1);


            return "adminWaiting";

        }
     */

        model.addAttribute("courseClientsList" , courseClientsList);

        CourseClientEntity courseClientEntity = new CourseClientEntity();
        courseClientEntity.defaultValue(courseClientEntity);
        model.addAttribute("modelCourseClient", courseClientEntity);

        List<ClientEntity> clientsList = clientRepository.findAll();
        model.addAttribute("clientsList", clientsList);
        log.info("Taille liste booking : " + courseClientsList.size());

        String message = "Validation en attente (" + courseClientsList.size() + ")";
        model.addAttribute("pageTitle", message);

        return "adminWaiting";
    }

    @GetMapping("/adminSummary")
    public String summary(Model model) {

        CourseEntity course = courseRepository.findById(1L).get();
        model.addAttribute("modelCourse", course);
    //  A revoir
        log.info("id course : " + course.getCrsId());
        List<CourseClientEntity> courseClientsList = courseClientRepository.findAllByBkId( course.getCrsId());
        model.addAttribute("courseClientsList" , courseClientsList);
        model.addAttribute("pageTitle", "Récapitulatif");

        return "adminSummary";
    }

    @RequestMapping("/adminValidation")
    public String validationBooking (Model model, @RequestParam Long bookingId) {
        log.info("bookingId : " + bookingId);
        CourseClientEntity courseClient = courseClientRepository.findById(bookingId).get();
        courseClient.setBkValidated(true);
        courseClientRepository.save(courseClient);

        return "redirect:/adminWaiting";
    }

    @RequestMapping("/adminDelete")
    public String deleteBooking (Model model, @RequestParam Long bookingId) {
        log.info("bookingId : " + bookingId);
        CourseClientEntity courseClient = courseClientRepository.findById(bookingId).get();
        courseClientRepository.delete(courseClient);
        return "redirect:/adminWaiting";
    }
}

















