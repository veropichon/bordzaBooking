package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.servlet.Registration;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    List<UserEntity> findByUsrEmailContaining(String usrEmail);

}
