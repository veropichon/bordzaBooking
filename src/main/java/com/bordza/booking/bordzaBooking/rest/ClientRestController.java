package com.bordza.booking.bordzaBooking.rest;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/api/clients")
    public List<ClientEntity> showClients(){

        return clientRepository.findAll();
    }

}
