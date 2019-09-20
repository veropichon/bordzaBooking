package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;
import com.bordza.booking.bordzaBooking.utils.SomeBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

import com.bordza.booking.bordzaBooking.utils.SomeBean;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Service
public class AdminCourseService {

    @Autowired
    CourseRepository courseRepository;

    public void update(CourseEntity inputCourseEntity, SomeBean someBean) throws IllegalArgumentException {

        log.info("courseEntity : " + inputCourseEntity.toString());

        CourseEntity courseEntity = courseRepository.findById(inputCourseEntity.getCrsId()).get();
        // boolean crsUnavailable = courseEntity.getCrsUnavailable();

        courseEntity = inputCourseEntity;

        // Mise à jour date de début en fonction de la date de début et de l'heure :
        // - someBean.fromDateUS       --par ex : 2019-12-31
        // - someBean.fromTimeHour
        // - someBean.fromTimeMinutes
        // 2019-09-21T11:30:00
        // Gestion zéro des minutes
        int tempMinutes = someBean.getFromTimeMinutes();
        String stringTempMinutes = String.valueOf(tempMinutes);
        if (tempMinutes < 10) {
            stringTempMinutes = "0" + stringTempMinutes;
        }
        LocalDateTime fromDate = LocalDateTime.parse(someBean.getFromDateUS() + "T" + someBean.getFromTimeHour() + ":" + stringTempMinutes + ":00");
        courseEntity.setCrsFromDate(fromDate);

        // Mise à jour date de fin en fonction de la nouvelle date de début et de la durée :
        // ToDate construit à partir de fromDate et Durée
        Long crsDurId = courseEntity.getDuration().getDurId();
        LocalDateTime toDate = fromDate.plusHours(crsDurId);
        courseEntity.setCrsToDate(toDate);

        courseRepository.save(courseEntity);

        log.info("Cours : " + courseEntity.toString());

    }
}
