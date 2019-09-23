package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import com.bordza.booking.bordzaBooking.services.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;

@Controller
public class AdminCourseSummaryController{
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

    private static final Logger log = LoggerFactory.getLogger("log AdminCourseSummaryController");


    @GetMapping("/adminSummary")
    public String summary(Model model) {

     // todo faire branchement avec Calendar

        CourseEntity course = courseRepository.findById(1L).get();
        model.addAttribute("modelCourse", course);
        //  A revoir
        log.info("id course : " + course.getCrsId());
        //List<CourseClientEntity> courseClientsList = courseClientRepository.findAllByBkId(course.getCrsId());
        List<CourseClientEntity> courseClientsList = courseClientRepository.findAllByCourseCrsId(course.getCrsId());
        log.info("taille liste : " + courseClientsList.size());
        model.addAttribute("nbvalidation", courseClientsList.size());

        // ----- tous bookings ont été supprimés

        if (courseClientsList.size() == 0) {
            String message = "Récapitulatif";
            String message1 = "Toutes vos réservations sur ce cours sont supprimées";
            model.addAttribute("pageTitle", message);

            model.addAttribute("information", message1);
            return "adminSummary";
        }
        model.addAttribute("courseClientsList" , courseClientsList);
        model.addAttribute("pageTitle", "Récapitulatif");
        return "adminSummary";
    }

    @RequestMapping("/adminSummaryValidation")
    public String validationBooking (Model model, @RequestParam Long bookingId) {
        //   log.info("bookingId : " + bookingId);
        CourseClientEntity courseClient = courseClientRepository.findById(bookingId).get();
        courseClient.setBkValidated(true);
        courseClientRepository.save(courseClient);

        return "redirect:/adminSummary";
    }

    @RequestMapping("/adminSummaryDelete")
    public String deleteBooking (Model model, @RequestParam Long bookingId) {
        //  log.info("bookingId : " + bookingId);
        CourseClientEntity courseClient = courseClientRepository.findById(bookingId).get();
        courseClientRepository.delete(courseClient);
        return "redirect:/adminSummary";
    }
    /*
    @RequestMapping("/adminSummaryCreateBooking")
    public String createBooking(Model model,
                              @ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                              @RequestParam Long courseId) {

        //  Liste des clients
        List<ClientEntity> clientsList = clientRepository.findAll();
        model.addAttribute("listClient",  clientsList);
        CourseEntity course = courseRepository.findById(courseId).get();
        model.addAttribute("modelCourse", course);

        courseClientEntity = new CourseClientEntity();
        courseClientEntity.defaultValue(courseClientEntity);
        model.addAttribute("modelCourseClient", courseClientEntity);

        // TODO récupérer l'ID client courant (cookie)
        // Pour l'instant : on suppose que le client est connecté et que c'est le client avec cliId = 2
        //ClientEntity client = clientRepository.findById(2L).get();
        //model.addAttribute("modelClient", client);

        model.addAttribute("modelLocation", new LocationEntity());
        model.addAttribute("modelDiscipline", new DisciplineEntity());

        model.addAttribute("pageTitle", "Réservation cours");

        return "adminReservation";
    }
    */

}
