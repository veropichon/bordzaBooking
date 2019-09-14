package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;

import com.bordza.booking.bordzaBooking.utils.SomeBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * Exception check when @POST from newCourse form
     * @param courseEntity course input form newCourse
     * @param clientEntity client input form newCourse
     * @param courseClientEntity courseClient input form newCourse
     * @throws IllegalArgumentException
     */

    public void saveCourse(CourseEntity courseEntity, ClientEntity clientEntity, CourseClientEntity courseClientEntity, SomeBean someBean) throws IllegalArgumentException {

        // String crsComment = courseEntity.getCrsComment();
        // boolean crsDeleted = courseEntity.getCrsDeleted();
        // String crsDesc = courseEntity.getCrsDesc();
        // boolean crsPublished = courseEntity.getCrsPublished();
        // boolean crsValidated = courseEntity.getCrsValidated();

        boolean crsUnavailable = courseEntity.getCrsUnavailable();
        boolean crsVip = courseEntity.getCrsVip();
        Long crsDisId = courseEntity.getDiscipline().getDisId();
        Long crsLvlId = courseEntity.getLevel().getLevId();
        Long crsLocId = courseEntity.getLocation().getLocId();

        // FromDate = FromDate par défaut avec l'heure saisie
        // LocalDateTime crsFromDate = LocalDateTime.of(courseEntity.getCrsFromDate().getYear(), courseEntity.getCrsFromDate().getMonth(), courseEntity.getCrsFromDate().getDayOfMonth(), someBean.getFromTime().getHour(), someBean.getFromTime().getMinute());
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

        // log.info("Cours : " + courseEntity.toString());
        // log.info("Booking : " + courseClientEntity.toString());

    }

    public void saveCourseClient(CourseClientEntity courseClientEntity, CourseEntity courseEntity, ClientEntity clientEntity)
            throws IllegalArgumentException {

        boolean bkVip = courseClientEntity.getBkVip();
        boolean bkMat = courseClientEntity.getBkMat();

        courseClientEntity.setClient(clientEntity);
        courseClientEntity.setCourse(courseEntity);
        courseClientEntity.setBkVip(bkVip);
        courseClientEntity.setBkMat(bkMat);

        courseClientRepository.save(courseClientEntity);

    }
}
