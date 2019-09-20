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

        // TODO gérer dates et heures
        // FromDate construite à partir de someBean.fromDateUS
        // FromDate = FromDate par défaut avec l'heure saisie
        // LocalDateTime crsFromDate = LocalDateTime.of(courseEntity.getCrsFromDate().getYear(), courseEntity.getCrsFromDate().getMonth(), courseEntity.getCrsFromDate().getDayOfMonth(), someBean.getFromTimeHour(), someBean.getFromTimeMinutes());
        // ToDate construit à partir de fromDate et Durée
        // Long crsDurId = courseEntity.getDuration().getDurId();
        // LocalDateTime crsToDate = crsFromDate.plusHours(crsDurId);

        // Titre construit à partir de la discipline et du lieu
        String crsTitle = courseEntity.getDiscipline().getDisLabel();
        crsTitle += ' ' + courseEntity.getLocation().getLocLabel();
        courseEntity.setCrsTitle(crsTitle);

        courseRepository.save(courseEntity);

        log.info("Cours : " + courseEntity.toString());

    }
}
