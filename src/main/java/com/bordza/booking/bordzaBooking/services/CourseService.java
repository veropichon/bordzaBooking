package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.bordza.booking.bordzaBooking.utils.SomeBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CourseClientRepository courseClientRepository;

    @Autowired
    MailService mailService;

    /**
     * Exception check when @POST from newCourse form
     *
     * @param courseEntity       course input form newCourse
     * @param clientEntity       client input form newCourse
     * @param courseClientEntity courseClient input form newCourse
     * @throws IllegalArgumentException
     */

    /* CREATION D'UN COURS */
    public void saveCourse(CourseEntity courseEntity, ClientEntity clientEntity, CourseClientEntity courseClientEntity, SomeBean someBean) throws IllegalArgumentException, MessagingException {

        boolean crsUnavailable = courseEntity.getCrsUnavailable();
        boolean crsVip = courseEntity.getCrsVip();
        Long crsDisId = courseEntity.getDiscipline().getDisId();
        Long crsLvlId = courseEntity.getLevel().getLevId();
        Long crsLocId = courseEntity.getLocation().getLocId();

        // FromDate = FromDate par défaut avec l'heure saisie
        LocalDateTime crsFromDate = LocalDateTime.of(courseEntity.getCrsFromDate().getYear(), courseEntity.getCrsFromDate().getMonth(), courseEntity.getCrsFromDate().getDayOfMonth(), someBean.getFromTimeHour(), someBean.getFromTimeMinutes());

        // ToDate construit à partir de fromDate et Durée
        Long crsDurId = courseEntity.getDuration().getDurId();
        LocalDateTime crsToDate = crsFromDate.plusHours(crsDurId);

        // Titre construit à partir de la discipline et du lieu
        String crsTitle = courseEntity.getDiscipline().getDisLabel();
        crsTitle += ' ' + courseEntity.getLocation().getLocLabel();

        courseEntity.setCrsTitle(crsTitle);
        courseEntity.setCrsFromDate(crsFromDate);
        courseEntity.setCrsToDate(crsToDate);
        courseEntity.setCrsVip(crsVip);
        courseRepository.save(courseEntity);

        courseClientEntity.setCourse(courseEntity);
        courseClientEntity.setClient(clientEntity);
        courseClientRepository.save(courseClientEntity);

        log.info("Cours : " + courseEntity.toString());
        log.info("Booking : " + courseClientEntity.toString());

    }
}
