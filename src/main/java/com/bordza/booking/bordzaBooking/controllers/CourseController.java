package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.config.ConfigTemplateEngine;
import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import com.bordza.booking.bordzaBooking.services.CourseService;
import com.bordza.booking.bordzaBooking.services.MailService;
import com.bordza.booking.bordzaBooking.utils.SomeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    private static final Logger log = LoggerFactory.getLogger("log CourseController");

    // Le visiteur a cliqué dans le calendrier pour ajouter un cours
    // Paramètre : start = date
    @RequestMapping("/newCourse")
    public String newCourse(Model model, @RequestParam String start) {

        CourseClientEntity courseClientEntity = new CourseClientEntity();
        courseClientEntity.defaultValue(courseClientEntity);
        model.addAttribute("modelCourseClient", courseClientEntity);

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
        course.defaultValue(course);

        LocalDateTime fromDate = LocalDateTime.parse(start);
        course.setCrsFromDate(fromDate);
        LocalDateTime toDate = fromDate.plusHours(1); // Par défaut : durée = 1 heure
        course.setCrsToDate(toDate);
        course.setCrsTitle("Titre par défaut");
        course.setCrsDesc("Description par défaut");
        LevelEntity level = levelRepository.findById(1L).get();
        course.setLevel(level);

        model.addAttribute("modelCourse", course);

        // TODO récupérer l'ID client courant (cookie)
        // Pour l'instant : on suppose que le client est connecté et que c'est le client avec cliId = 1
        ClientEntity client = clientRepository.findById(1L).get();
        model.addAttribute("modelClient", client);

        SomeBean someBean = new SomeBean();
        // someBean.setFromTime(LocalTime.of(course.getCrsFromDate().getHour(), course.getCrsFromDate().getMinute())); // TODO à supprimer si on utilise le format input type number
        someBean.setFromTimeHour(course.getCrsFromDate().getHour());
        someBean.setFromTimeMinutes(course.getCrsFromDate().getMinute());
        someBean.setFromDateTime(course.getCrsFromDate());
        // String.valueOf(number)
        model.addAttribute("someBean", someBean);

        // log.info("course.getCrsFromDate() : " + course.getCrsFromDate());
        // log.info("bean FromTime : " + someBean.getFromTime());
        // log.info("bean FromTimeHour : " + someBean.getFromTimeHour());
        // log.info("bean FromTimeMinutes : " + someBean.getFromTimeMinutes());
        // log.info("bean FromDateTime : " + someBean.getFromDateTime());

        model.addAttribute("pageTitle", "Nouveau cours");

        return "newCourse";
    }

    // Sauvegarde du cours (table Course) et de la réservation (table Booking)
    @PostMapping("/newCourse")
    public String saveCourseAndBooking(@ModelAttribute("modelCourse") CourseEntity courseEntity,
                                       @ModelAttribute("modelClient") ClientEntity clientEntity,
                                       @ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                                       @ModelAttribute("someBean") SomeBean someBean,
                                       BindingResult result, ModelMap model
                                       ) throws MessagingException  {



            courseEntity.defaultValue(courseEntity);
            courseClientEntity.defaultValue(courseClientEntity);

            // création du cours
            courseService.saveCourse(courseEntity, clientEntity, courseClientEntity, someBean);

            // Récupération email du client (table user)
            // String emailClient = clientEntity.getUser().getUsrEmail();
            // log.info("email client : " + emailClient);

            // envoi de l'email au client
            MimeMessage msg = null;
            // msg = mailService.buildEmail(emailClient, "Votre nouveau cours", "blablatruc", false);
            msg = mailService.buildEmail("mariehelene.delteil@gmail.com", "Votre nouveau cours", "blablatruc", false);
            mailService.sendEmail(msg);

            // envoi de l'email à l'administrateur
            String url = "redirect:/courseSummary?bookingId=" + String.valueOf(courseClientEntity.getBkId());
            return url;

    }

    // Récapitulatif du cours créé
    // Paramètre : ID du booking (table booking)
    @RequestMapping("/courseSummary")
        public String courseSummary(Model model,
                                    @RequestParam Long bookingId) {
            CourseClientEntity booking = courseClientRepository.findById(bookingId).get();

        // log.info("id cours : " + booking.getCourse().getCrsFromDate());
        model.addAttribute("modelCourseClient", booking);
        model.addAttribute("pageTitle", "Récapitulatif");
        return "courseSummary";
    }

    // Demande d'inscription à un cours existant
    // Paramètre : ID du cours (table course)
    @RequestMapping("/reservation")
    public String reservation(Model model,
                              @ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                              @RequestParam Long courseId) {

        CourseEntity course = courseRepository.findById(courseId).get();
        model.addAttribute("modelCourse", course);

        courseClientEntity = new CourseClientEntity();
        courseClientEntity.defaultValue(courseClientEntity);
        model.addAttribute("modelCourseClient", courseClientEntity);

        // TODO récupérer l'ID client courant (cookie)
        // Pour l'instant : on suppose que le client est connecté et que c'est le client avec cliId = 2
        ClientEntity client = clientRepository.findById(2L).get();
        model.addAttribute("modelClient", client);

        model.addAttribute("modelLocation", new LocationEntity());
        model.addAttribute("modelDiscipline", new DisciplineEntity());

        model.addAttribute("pageTitle", "Réservation");

        return "reservation";
    }

    // Sauvegarde de l'inscription à un cours existant (booking)
    @PostMapping("/reservation")
    public String saveBooking(@ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                              @ModelAttribute("modelCourse") CourseEntity courseEntity,
                              @ModelAttribute("modelClient") ClientEntity clientEntity,

                              BindingResult result, ModelMap model) {

        try {

            courseClientEntity.defaultValue(courseClientEntity);

            courseService.saveCourseClient(courseClientEntity, courseEntity, clientEntity);

            String url = "redirect:/reservationSummary?bookingId=" + String.valueOf(courseClientEntity.getBkId());
            return url;
        }
        catch (IllegalArgumentException e) {

            return "reservation";
        }
    }

    // Récapitulatif de l'ajout à un cours existant
    // Paramètre : ID du booking (table booking)
    @RequestMapping("/reservationSummary")
    public String reservationSummary(Model model,
                                @RequestParam Long bookingId) {
        CourseClientEntity booking = courseClientRepository.findById(bookingId).get();

        model.addAttribute("modelCourseClient", booking);
        model.addAttribute("pageTitle", "Récapitulatif");
        return "reservationSummary";
    }
}
