package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.CourseEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public UserEntity findUserEntityByUsrRoleIs(String role);
}
