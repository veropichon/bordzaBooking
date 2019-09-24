/**
 * ADMIN :
 * - CREATION / MODIFICATION / PUBLICATION D'UN COURS
 */

package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.services.ClientIdService;
import com.bordza.booking.bordzaBooking.utils.SomeBean;
import com.bordza.booking.bordzaBooking.utils.util;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bordza.booking.bordzaBooking.config.ConfigTemplateEngine;
import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.*;
// import com.bordza.booking.bordzaBooking.services.CourseService;
import com.bordza.booking.bordzaBooking.services.AdminCourseService;
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
public class AdminCourseController {

    @Autowired
    ConfigTemplateEngine configTemplateEngine;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CourseClientRepository courseClientRepository;

    @Autowired
    DisciplineRepository disciplineRepository;

    @Autowired
    DurationRepository durationRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    AdminCourseService adminCourseService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Autowired
    private ClientIdService idService;

    private static final Logger log = LoggerFactory.getLogger("log AdminCourseController");

    // --------------------------------------------------------------------------------------------------------

    // MODIFICATION/PUBLICATION D'UN COURS : CHARGEMENT
    @GetMapping("/adminPublishCourse")
    public String adminPublishCourse(Model model,
                                     @RequestParam Long courseId, String ori) {
        log.info("id course : " + courseId);
        log.info("ori : " + ori);
        CourseEntity courseEntity = courseRepository.findByCrsId(courseId);
        model.addAttribute("modelCourse", courseEntity);

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

        SomeBean someBean = new SomeBean();
        someBean.setOrigine(ori);

        // extraction date
        someBean.setFromDate(courseEntity.getCrsFromDate().toLocalDate());
        String tempDay = "";
        if (courseEntity.getCrsFromDate().getDayOfMonth() < 10) {
            tempDay = "0" + courseEntity.getCrsFromDate().getDayOfMonth();
        } else {
            tempDay = Integer.toString(courseEntity.getCrsFromDate().getDayOfMonth());
        }
        String tempMonth = "";
        if (courseEntity.getCrsFromDate().getMonthValue() < 10) {
            tempMonth = "0" + courseEntity.getCrsFromDate().getMonthValue();
        } else {
            tempMonth = Integer.toString(courseEntity.getCrsFromDate().getMonthValue());
        }
        someBean.setFromDateUS(courseEntity.getCrsFromDate().getYear() + "-" + tempMonth + "-" + tempDay);
        // extraction heure
        someBean.setFromTimeHour(courseEntity.getCrsFromDate().getHour());
        someBean.setFromTimeMinutes(courseEntity.getCrsFromDate().getMinute());
        someBean.setFromDateTime(courseEntity.getCrsFromDate());
        model.addAttribute("someBean", someBean);

        model.addAttribute("pageTitle", "Publication d'un cours");
        model.addAttribute("bookingToValid", courseClientRepository.findAllByBkValidated(false).size());


        return "adminPublishCourse";
    }

    // --------------------------------------------------------------------------------------------------------

    // MODIFICATION D'UN COURS : SAUVEGARDE

    @PostMapping("/adminPublishCourse")
    public String saveCourseAndBooking(@ModelAttribute("modelCourse") CourseEntity courseEntity,
                                       @ModelAttribute("someBean") SomeBean someBean,
                                       BindingResult result, ModelMap model
    ) throws MessagingException  {

        model.addAttribute("bookingToValid", courseClientRepository.findAllByBkValidated(false).size());

        // mise à jour du cours
        adminCourseService.update(courseEntity, someBean);
        Long id = courseEntity.getCrsId();

        String url = "";
        if (someBean.getOrigine().equals("1") ) {
            url = "redirect:/adminWaiting";
            return url;
        } else {
            url = "redirect:/adminSummary?courseId=" + id;
            return url;
        }
    }

    // --------------------------------------------------------------------------------------------------------

    // CREATION D'UN COURS : CHARGEMENT
    // L'admin a cliqué dans le calendrier pour ajouter un cours
    // Paramètre : start = date

    @GetMapping("/adminNewCourse")
    public String adminNewCourse(Model model, @RequestParam String start) {

        // Identification du visiteur : doit être égal à l'admin
        Long idConnected = idService.getClientId();
        if (idConnected == 0) { return "redirect:/login"; }
        if (idConnected != -1) { return "redirect:/calendar"; }

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
        course.setCrsTitle("");
        course.setCrsDesc("");
        LevelEntity level = levelRepository.findById(1L).get();
        course.setLevel(level);

        model.addAttribute("modelCourse", course);

        SomeBean someBean = new SomeBean();
        someBean.setFromTimeHour(course.getCrsFromDate().getHour());
        someBean.setFromTimeMinutes(course.getCrsFromDate().getMinute());
        someBean.setFromDateTime(course.getCrsFromDate());
        model.addAttribute("someBean", someBean);

        model.addAttribute("pageTitle", "Création d'un cours");

        return "adminNewCourse";
    }

    // --------------------------------------------------------------------------------------------------------

    // CREATION D'UN COURS : SAUVEGARDE

    @PostMapping("/adminNewCourse")
    public String saveCourse(@ModelAttribute("modelCourse") CourseEntity courseEntity,
                                       @ModelAttribute("someBean") SomeBean someBean,
                                       BindingResult result, ModelMap model
    ) {

        log.info("AdminCourseController courseEntity");
        log.info(courseEntity.getCrsTitle());

        // création du cours
        adminCourseService.saveCourse(courseEntity, someBean);
        Long courseId = courseEntity.getCrsId();

        return "admincalendar";

    }
}
