package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseClientRepository;
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

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CourseClientRepository courseClientRepository;

    /* MODIFICATION/PUBLICATION D'UN COURS */
    public void update(CourseEntity inputCourseEntity, SomeBean someBean) throws IllegalArgumentException {

        // log.info("courseEntity : " + inputCourseEntity.toString());

        CourseEntity courseEntity = courseRepository.findById(inputCourseEntity.getCrsId()).get();
        courseEntity = inputCourseEntity;

        // Mise à jour date de début en fonction de la date de début et de l'heure :
        // - someBean.fromDateUS       --par ex : 2019-12-31
        // - someBean.fromTimeHour
        // - someBean.fromTimeMinutes
        // 2019-09-21T11:30:00

        // Gestion zéro de l'heure
        int tempHour = someBean.getFromTimeHour();
        String stringTempHour = String.valueOf(tempHour);
        if (tempHour < 10) {
            stringTempHour = "0" + stringTempHour;
        }
        // Gestion zéro des minutes
        int tempMinutes = someBean.getFromTimeMinutes();
        String stringTempMinutes = String.valueOf(tempMinutes);
        if (tempMinutes < 10) {
            stringTempMinutes = "0" + stringTempMinutes;
        }
        LocalDateTime fromDate = LocalDateTime.parse(someBean.getFromDateUS() + "T" + stringTempHour + ":" + stringTempMinutes + ":00");
        courseEntity.setCrsFromDate(fromDate);

        // Mise à jour date de fin en fonction de la nouvelle date de début et de la durée :
        // ToDate construit à partir de fromDate et Durée
        Long crsDurId = courseEntity.getDuration().getDurId();
        LocalDateTime toDate = fromDate.plusHours(crsDurId);
        courseEntity.setCrsToDate(toDate);

        courseRepository.save(courseEntity);

        log.info("Cours creator id: " + inputCourseEntity.getCrsCreatorId());

        // si le cours est publié et que le créateur n'est pas l'admin => inscrire automatiquement le créateur
        boolean isPublished = courseEntity.getCrsPublished();
        Long creatorId = courseEntity.getCrsCreatorId();
        ClientEntity clientEntity = clientRepository.findByCliId(creatorId);

        if ((isPublished == true) && (creatorId != 0)) {
            CourseClientEntity courseClientEntity = courseClientRepository.findByCourseIsAndClientIs(courseEntity, clientEntity);
            courseClientEntity.setBkValidated(true);
            courseClientRepository.save(courseClientEntity);
        }

    }

    /* ENREGISTREMENT D'UN NOUVEAU COURS CREE PAR L'ADMIN */
    public void saveCourse(CourseEntity courseEntity, SomeBean someBean) throws IllegalArgumentException {

        // FromDate = FromDate par défaut avec l'heure saisie
        LocalDateTime crsFromDate = LocalDateTime.of(courseEntity.getCrsFromDate().getYear(), courseEntity.getCrsFromDate().getMonth(), courseEntity.getCrsFromDate().getDayOfMonth(), someBean.getFromTimeHour(), someBean.getFromTimeMinutes());

        // ToDate construit à partir de fromDate et Durée
        Long crsDurId = courseEntity.getDuration().getDurId();
        LocalDateTime crsToDate = crsFromDate.plusHours(crsDurId);

        courseEntity.setCrsFromDate(crsFromDate);
        courseEntity.setCrsToDate(crsToDate);
        courseEntity.setCrsDeleted(false);
        courseRepository.save(courseEntity);

    }
}
