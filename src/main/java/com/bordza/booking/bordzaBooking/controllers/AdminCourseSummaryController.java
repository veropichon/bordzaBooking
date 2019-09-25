package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import com.bordza.booking.bordzaBooking.services.CourseService;
import com.bordza.booking.bordzaBooking.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;

@Controller
public class AdminCourseSummaryController {
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
    MailService mailService;

    private static final Logger log = LoggerFactory.getLogger("log AdminCourseSummaryController");


    @GetMapping("/adminSummary")
    public String summary(Model model, @RequestParam Long courseId) {

        CourseEntity course = courseRepository.findById(courseId).get();
        model.addAttribute("modelCourse", course);

        List<CourseClientEntity> courseClientsList = courseClientRepository.findAllByCourseCrsId(course.getCrsId());
        log.info("taille liste : " + courseClientsList.size());
        model.addAttribute("nbvalidation", courseClientsList.size());

        // ----- tous bookings ont été supprimés

        if (courseClientsList.size() == 0) {
            String message = "Récapitulatif";
            String message1 = "Aucune réservation sur ce cours";
            model.addAttribute("pageTitle", message);

            model.addAttribute("information", message1);
            return "adminSummary";
        }
        model.addAttribute("courseClientsList", courseClientsList);
        model.addAttribute("pageTitle", "Récapitulatif");
        model.addAttribute("bookingToValid", courseClientRepository.findAllByBkValidated(false).size());

        return "adminSummary";
    }

    // Validation d'un booking sur récapitulatif Cours admin

    @RequestMapping("/adminSummaryValidation")
    public String validationBooking(Model model, @RequestParam Long bookingId) {
        CourseClientEntity courseClient = courseClientRepository.findById(bookingId).get();
        courseClient.setBkValidated(true);
        Long courseId = courseClient.getCourse().getCrsId();
        courseClientRepository.save(courseClient);

        return "redirect:/adminSummary?courseId="+ courseId;
    }

    // Suppression d'un booking sur récapitulatif Cours admin

    @RequestMapping("/adminSummaryDelete")
    public String deleteBooking(Model model, @RequestParam Long bookingId) {
        CourseClientEntity courseClient = courseClientRepository.findById(bookingId).get();
        Long courseId = courseClient.getCourse().getCrsId();
        courseClientRepository.delete(courseClient);
        log.info("id cours : " + courseId);
        return "redirect:/adminSummary?courseId="+ courseId;

    }

    //   Ajout d'un particpant à un cours existant Admin
    @RequestMapping("/adminReservation")
    public String createBooking(Model model,
                                @ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                                @RequestParam Long courseId) {

        //  Liste des clients
        List<ClientEntity> clientsList = clientRepository.findAll();
        //List<ClientEntity> clientsList = clientRepository.findAllByCliLastnameIsNotOrderByCliLastnameDesc(String );
        model.addAttribute("listClient", clientsList);
        CourseEntity course = courseRepository.findById(courseId).get();
        model.addAttribute("modelCourse", course);

        courseClientEntity = new CourseClientEntity();
        courseClientEntity.defaultValue(courseClientEntity);
        model.addAttribute("modelCourseClient", courseClientEntity);

        // TODO récupérer l'ID client courant (cookie)
        // Pour l'instant : on suppose que le client est connecté et que c'est le client avec cliId = 2
        //ClientEntity client = clientRepository.findById(2L).get();
        //model.addAttribute("modelClient", client);

        //.addAttribute("modelLocation", new LocationEntity());
        //model.addAttribute("modelDiscipline", new DisciplineEntity());

        model.addAttribute("pageTitle", "Réservation cours");

        return "adminReservation";
    }

    // Sauvegarde de l'inscription à un cours existant (booking)
    @PostMapping("/adminReservation")
    public String saveBooking(@ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                              @ModelAttribute("modelCourse") CourseEntity courseEntity,

                              BindingResult result, ModelMap model) throws MessagingException {

        try {
            log.info("cliId : " + courseClientEntity.getClient().getCliId());
            log.info("coursId : " + courseEntity.getCrsId());

            courseClientEntity.defaultValue(courseClientEntity);
            courseClientEntity.setCourse(courseEntity);
            courseClientEntity.setBkValidated(true);
            // client is already populated
            courseClientRepository.save(courseClientEntity);

            // envoi de l'email au client
            Long current_CliId = courseClientEntity.getClient().getCliId();
            String clientEmail = clientRepository.findById(current_CliId).get().getUser().getUsrEmail();
            String clientLastname = clientRepository.findById(current_CliId).get().getCliLastname();
            String clientFirstname = clientRepository.findById(current_CliId).get().getCliFirstname();

            Long current_CrsId = courseClientEntity.getCourse().getCrsId();
            String discipline = courseClientRepository.findById(current_CrsId).get().getCourse().getDiscipline().getDisLabel();
            log.info("discipline : " + discipline);

            String subject = "Bordza - Votre demande d'inscription à un cours";
            String contents = "Bonjour " + clientFirstname + " " + clientLastname + ",\n\n";
            contents += "Votre demande d'inscription à un cours de "+ discipline + " a bien été enregistrée.\n\n";
            contents += "L'équipe Bordza";

            MimeMessage msg = null;
            msg = mailService.buildEmail(clientEmail, subject, contents, false);
            mailService.sendEmail(msg);

            String url = "redirect:/adminSummary?courseId=" +courseClientEntity.getCourse().getCrsId();
            return url;


        } catch (IllegalArgumentException e) {

            return "AdminReservation";
        }
    }

}
