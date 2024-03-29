package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.config.ConfigTemplateEngine;
import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
import com.bordza.booking.bordzaBooking.services.ClientIdService;
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
import java.time.LocalDate;
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

    @Autowired
    private ClientIdService idService;

    private static final Logger log = LoggerFactory.getLogger("log CourseController");

    // Le visiteur a cliqué dans le calendrier pour ajouter un cours
    // Paramètre : start = date
    @RequestMapping("/newCourse")
    public String newCourse(Model model, @RequestParam String start) {

        // Identification du visiteur
        Long idConnected = idService.getClientId();
        if (idConnected == -1) { return "redirect:/admincalendar"; }
        if (idConnected == 0) { return "redirect:/login"; }
        ClientEntity client = clientRepository.findById(idConnected).get();
        model.addAttribute("modelClient", client);

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
        course.setCrsCreatorId(idConnected);
        LocalDateTime fromDate = LocalDateTime.parse(start);
        course.setCrsFromDate(fromDate);
        LocalDateTime toDate = fromDate.plusHours(1); // Par défaut : durée = 1 heure
        course.setCrsToDate(toDate);
        course.setCrsTitle("Titre par défaut");
        course.setCrsDesc("Description par défaut");
        LevelEntity level = levelRepository.findById(1L).get();
        course.setLevel(level);
        model.addAttribute("modelCourse", course);

        SomeBean someBean = new SomeBean();
        someBean.setFromTimeHour(course.getCrsFromDate().getHour());
        someBean.setFromTimeMinutes(course.getCrsFromDate().getMinute());
        someBean.setFromDateTime(course.getCrsFromDate());
        model.addAttribute("someBean", someBean);

        model.addAttribute("pageTitle", "Proposition de cours");

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

        // envoi de l'email au client
        Long creatorId = courseEntity.getCrsCreatorId();
        String clientEmail = clientRepository.findById(creatorId).get().getUser().getUsrEmail();
        String clientLastname = clientRepository.findById(creatorId).get().getCliLastname();
        String clientFirstname = clientRepository.findById(creatorId).get().getCliFirstname();
        String subject = "Bordza - Votre demande de cours";
        String contents = "Bonjour " + clientFirstname + " " + clientLastname + ",\n\n";
        contents += "Votre demande de cours a bien été transmise.\nVous recevrez très prochainement un email une fois que nous l'aurons validé.\n\n";
        contents += "L'équipe Bordza";
        MimeMessage msg = null;
        msg = mailService.buildEmail(clientEmail, subject, contents, false);
        mailService.sendEmail(msg);

        // envoi de l'email à l'administrateur
        String adminEmail = userRepository.findUserEntityByRoleIs("ADMIN").getUsrEmail();
        subject = "Nouveau cours";
        contents = "Bonjour,\n\n";
        contents += "Un nouveau cours est à valider.\n";
        msg = mailService.buildEmail(adminEmail, subject, contents, false);
        mailService.sendEmail(msg);

        String url = "redirect:/courseSummary?bookingId=" + String.valueOf(courseClientEntity.getBkId());
        return url;

    }

    // Récapitulatif du cours créé
    // Paramètre : Long bookingId = ID du booking (table booking)
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
    // Paramètre : Long courseId = ID du cours (table course)
    @RequestMapping("/reservation")
    public String reservation(Model model,
                              @ModelAttribute("modelCourseClient") CourseClientEntity courseClientEntity,
                              @RequestParam Long courseId) {

        // Identification du visiteur
        Long idConnected = idService.getClientId();
        if (idConnected == -1) { return "redirect:/admincalendar"; }
        if (idConnected == 0) { return "redirect:/login"; }
        ClientEntity client = clientRepository.findById(idConnected).get();
        model.addAttribute("modelClient", client);

        CourseEntity course = courseRepository.findById(courseId).get();
        model.addAttribute("modelCourse", course);

        courseClientEntity = new CourseClientEntity();
        courseClientEntity.defaultValue(courseClientEntity);
        model.addAttribute("modelCourseClient", courseClientEntity);

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

                              BindingResult result, ModelMap model) throws MessagingException {

        try {

            courseClientEntity.defaultValue(courseClientEntity);

            // builing the relationship
            courseClientEntity.setClient(clientEntity);
            courseClientEntity.setCourse(courseEntity);
            courseClientRepository.save(courseClientEntity);

            // envoi de l'email au client
            Long current_CliId = clientEntity.getCliId();
            String clientEmail = clientRepository.findById(current_CliId).get().getUser().getUsrEmail();
            String clientLastname = clientRepository.findById(current_CliId).get().getCliLastname();
            String clientFirstname = clientRepository.findById(current_CliId).get().getCliFirstname();
            String subject = "Bordza - Votre demande d'inscription à un cours";
            String contents = "Bonjour " + clientFirstname + " " + clientLastname + ",\n\n";
            contents += "Votre demande d'inscription à un cours a bien été transmise.\nVous recevrez très prochainement un email une fois que nous l'aurons validée.\n\n";
            contents += "L'équipe Bordza";

            MimeMessage msg = null;
            msg = mailService.buildEmail(clientEmail, subject, contents, false);
            mailService.sendEmail(msg);

            // envoi de l'email à l'administrateur
            String adminEmail = userRepository.findUserEntityByRoleIs("ADMIN").getUsrEmail();
            subject = "Demande d'inscription à un cours";
            contents = "Bonjour,\n\n";
            contents += "Une nouvelle demande d'inscription est à valider.\n";
            msg = mailService.buildEmail(adminEmail, subject, contents, false);
            mailService.sendEmail(msg);

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

    // Récapitulatif des cours d'un client

    @GetMapping("/listCourses")
    public String listCourses(Model model) {

        // Identification du visiteur
        Long idConnected = idService.getClientId();
        if (idConnected == -1) { return "redirect:/admincalendar"; }
        if (idConnected == 0) { return "redirect:/login"; }
        ClientEntity clientEntity = clientRepository.findById(idConnected).get();
        // model.addAttribute("modelClient", client);

        List<CourseClientEntity> courseClientsList = courseClientRepository.findAllByClientCliIdAndBkValidated(clientEntity.getCliId(), true);
        log.info("taille liste : " + courseClientsList.size());
        model.addAttribute("nbcours", courseClientsList.size());

        //     Aucun cours à visualiser

        if (courseClientsList.size() == 0) {
            String message = "Mes cours";
            String message1 = "Aucune participation aux cours";
            model.addAttribute("pageTitle", message);
            model.addAttribute("information", message1);
            return "listCourses";
        }
        model.addAttribute("courseClientsList" , courseClientsList);
        LocalDate currentDate = LocalDateTime.now().toLocalDate();
        model.addAttribute("todaysdate",currentDate );
        model.addAttribute("pageTitle", "Mes cours");
        return "listCourses";
    }
}
