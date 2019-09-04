package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.DisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Long> {
}
