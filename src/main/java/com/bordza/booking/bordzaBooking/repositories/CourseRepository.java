package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    public List<CourseEntity> findByCrsFromDateGreaterThanEqualAndCrsToDateLessThanEqual(LocalDateTime start, LocalDateTime end);

    public CourseEntity findByCrsId(Long id);

}
