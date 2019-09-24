package com.bordza.booking.bordzaBooking.repositories;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    public ClientEntity findByUser(UserEntity user);

    public ClientEntity findByCliId(Long cliId);

  //  public ClientEntity findAllOrderByCliLastnameDesc();

}