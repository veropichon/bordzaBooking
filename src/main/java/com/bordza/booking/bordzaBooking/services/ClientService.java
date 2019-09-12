package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import com.bordza.booking.bordzaBooking.utils.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientValidator clientValidator;


    /**
     * Exception check when @POST from inscription form
     *
     * @param userEntity   user input form inscription
     * @param clientEntity client input form inscription
     * @throws IllegalArgumentException
     */
    public void save(UserEntity userEntity, ClientEntity clientEntity) throws IllegalArgumentException {

        clientValidator.clientValidator(clientEntity, userEntity);

        userRepository.save(userEntity);

        clientEntity.setUser(userEntity);
        clientRepository.save(clientEntity);
    }

    public void update(UserEntity inputUserEntity, ClientEntity inputClientEntity) throws IllegalArgumentException {

        clientValidator.clientValidator(inputClientEntity, inputUserEntity);

        ClientEntity clientEntity = clientRepository.findById(inputClientEntity.getCliId()).get();
        UserEntity userEntity = userRepository.findById(clientEntity.getUser().getUsrId()).get();

        if ( inputUserEntity.getUsrPwd() == ""){
            inputUserEntity.setUsrPwd(userEntity.getUsrPwd());
        }

        userEntity = inputUserEntity;
        clientEntity = inputClientEntity;

        userRepository.save(userEntity);

        clientEntity.setUser(userEntity);
        clientRepository.save(clientEntity);
    }
}
