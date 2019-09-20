package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class ClientIdService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    public Long getClientId() {
        Long id;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {

            String username = ((UserDetails) principal).getUsername();
            UserEntity user = userRepository.findByUsrEmail(username);
            if (user.getRole().equals("ADMIN")) {
                id = -1L;
            } else {
                ClientEntity client = clientRepository.findByUser(user);
                id = client.getCliId();
            }
        } else {
            String username = principal.toString();
            if (username.equals("anonymousUser")) {
                id = 0L;
            } else {
                UserEntity user = userRepository.findByUsrEmail(username);
                if (user.getRole().equals("ADMIN")) {
                    id = -1L;
                } else {
                    ClientEntity client = clientRepository.findByUser(user);
                    id = client.getCliId();
                }
            }
        }
        return id;
    }
}
