/**
 * ADMIN :
 * - MODIFICATION / PUBLICATION D'UN COURS
 */

package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.*;
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
    AdminCourseService adminCourseService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    private static final Logger log = LoggerFactory.getLogger("log CourseController");

    // MODIFICATION D'UN COURS : CHARGEMENT
    @GetMapping("/adminPublishCourse")
    public String adminPublishCourse(Model model,
                                     @RequestParam Long courseId, String ori) {

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

        return "adminPublishCourse";
    }

    // MODIFICATION D'UN COURS : SAUVEGARDE
    @PostMapping("/adminPublishCourse")
    public String saveCourseAndBooking(@ModelAttribute("modelCourse") CourseEntity courseEntity,
                                       @ModelAttribute("someBean") SomeBean someBean,
                                       BindingResult result, ModelMap model
    ) throws MessagingException  {


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
}