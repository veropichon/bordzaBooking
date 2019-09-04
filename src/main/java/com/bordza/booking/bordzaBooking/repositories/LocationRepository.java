package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
