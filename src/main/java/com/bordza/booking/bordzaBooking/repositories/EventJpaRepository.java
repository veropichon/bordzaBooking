package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventJpaRepository extends JpaRepository<Event, Long> {

    /* Note these two methods do the same thing.  The @Repository annotation handles both*/


    /* This one uses a JPQL */
    public List<Event> findByStartGreaterThanEqualAndFinishLessThanEqual(LocalDateTime start, LocalDateTime end);


    /* This one uses an @Query */
    //@Query("SELECT b FROM Event b WHERE b.start >= ?1 AND b.finish <= ?2")
    //public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);

}
