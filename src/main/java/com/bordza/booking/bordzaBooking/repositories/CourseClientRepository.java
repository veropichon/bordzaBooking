package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseClientRepository extends JpaRepository<CourseClientEntity, Long> {
    List<CourseClientEntity> findAllByBkValidated(boolean b);

    List<CourseClientEntity> findAllByBkId(Long crsId);

    CourseClientEntity findByCourseIsAndClientIs(CourseEntity courseEntity, ClientEntity clientEntity);

    List<CourseClientEntity> findAllByCourseCrsId(Long crsId);

    List<CourseClientEntity> findAllByClientCliId(Long cliId);

    List<CourseClientEntity> findAllByClientCliIdOrderByCourseCrsFromDateDesc(Long cliId);
}
