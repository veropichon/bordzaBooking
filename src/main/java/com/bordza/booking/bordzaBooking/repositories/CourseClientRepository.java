package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.CourseClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseClientRepository extends JpaRepository<CourseClientEntity, Long> {
}
