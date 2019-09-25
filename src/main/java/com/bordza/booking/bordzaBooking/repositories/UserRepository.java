package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsrEmail(String email);
    public UserEntity findUserEntityByRoleIs(String role);
    public List<UserEntity> findByUsrEmailContaining(String usrEmail);
}
