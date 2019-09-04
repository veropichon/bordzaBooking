package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.PracticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<PracticeEntity, Long> {
}
