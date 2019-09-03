package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    /**
     * Exception check when @POST from inscription form
     * @param userEntity user input form inscription
     * @param clientEntity client input form inscription
     * @throws IllegalArgumentException
     */
    public void saveClient(UserEntity userEntity, ClientEntity clientEntity) throws IllegalArgumentException {

        String clientlvl = clientEntity.getLevel().getLevClientLabel();
        String usrEmail = StringUtils.trim(userEntity.getUsrEmail());
        String usrPwd = userEntity.getUsrPwd();

        log.info("User : " + userEntity.toString());
        log.info("Client : " + clientEntity.toString());

        if (StringUtils.isBlank(usrEmail)) {
            throw new IllegalArgumentException("user email is invalid");
        }

        if(StringUtils.isEmpty(usrPwd) /* FIXME RULES */) {
            throw new IllegalArgumentException("password doesn't comply with...");
        }

        // TODO verif!

        userRepository.save(userEntity);

        clientEntity.setUser(userEntity);
        clientRepository.save(clientEntity);
    }
}
