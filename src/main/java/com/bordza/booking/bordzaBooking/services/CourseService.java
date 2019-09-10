package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseClientRepository;
import com.bordza.booking.bordzaBooking.repositories.CourseRepository;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;

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

    public void saveCourse(CourseEntity courseEntity, ClientEntity clientEntity, CourseClientEntity courseClientEntity) throws IllegalArgumentException {

        // TODO : récupérer heures horaires pour mettre à jour les 2 dates (datetime)

        // String crsComment = courseEntity.getCrsComment();
        // boolean crsDeleted = courseEntity.getCrsDeleted();
         // String crsDesc = courseEntity.getCrsDesc();
        LocalDateTime crsFromDate = courseEntity.getCrsFromDate();
        // boolean crsPublished = courseEntity.getCrsPublished();
        // String crsTitle = courseEntity.getCrsTitle();
        LocalDateTime crsToDate = courseEntity.getCrsToDate();
        boolean crsUnvailable = courseEntity.getCrsUnavailable();
        // boolean crsValidated = courseEntity.getCrsValidated();
        boolean crsVip = courseEntity.getCrsVip();
        Long crsDisId = courseEntity.getDiscipline().getDisId();
        Long crsLvlId = courseEntity.getLevel().getLevId();
        Long crsLocId = courseEntity.getLocation().getLocId();


        log.info("Cours : " + courseEntity.toString());
        // log.info("Booking : " + courseClientEntity.toString());

        courseRepository.save(courseEntity);

        courseClientEntity.setCourse(courseEntity);
        courseClientEntity.setClient(clientEntity);
        courseClientRepository.save(courseClientEntity);



    }
}
