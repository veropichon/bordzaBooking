package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    public List<CourseEntity> findByCrsFromDateGreaterThanEqualAndCrsToDateLessThanEqual(LocalDateTime start, LocalDateTime end);

    /* This one uses an @Query */
    //@Query("SELECT b FROM Event b WHERE b.start >= ?1 AND b.finish <= ?2")
    //public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
