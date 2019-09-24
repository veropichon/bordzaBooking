package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import com.bordza.booking.bordzaBooking.utils.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Service
public class ClientService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientValidator clientValidator;

    @Autowired
    StorageService storageService;
    /**
     * Exception check when @POST from inscription form
     *
     * @param userEntity   user input form inscription
     * @param clientEntity client input form inscription
     * @throws IllegalArgumentException
     */
    public void save(UserEntity userEntity, ClientEntity clientEntity, MultipartFile file, RedirectAttributes redirectAttributes) throws IllegalArgumentException {

        clientValidator.clientValidator(clientEntity, userEntity);

        log.info("role : " + userEntity.getRole());
        userRepository.save(userEntity);

        String urlPicture = storageService.store(file, redirectAttributes, userEntity);

        if (urlPicture != null) {
            clientEntity.setCliUrlPicture(storageService.getExternalPicturesDir() + userEntity.getUsrId() + "_" + urlPicture);
        }

        clientEntity.setUser(userEntity);
        clientRepository.save(clientEntity);
    }

    public void update(UserEntity inputUserEntity, ClientEntity inputClientEntity, String urlPicture) throws IllegalArgumentException {

        clientValidator.clientValidator(inputClientEntity, inputUserEntity);

        ClientEntity clientEntity = clientRepository.findById(inputClientEntity.getCliId()).get();
        UserEntity userEntity = userRepository.findById(clientEntity.getUser().getUsrId()).get();


        if (inputUserEntity.getUsrPwd() == "") {
            inputUserEntity.setUsrPwd(userEntity.getUsrPwd());
        }

        log.info("update urlPicture 1 : " + urlPicture);

        userEntity = inputUserEntity;
        clientEntity = inputClientEntity;

        //userEntity.setUsrId(inputClientEntity.getUser().getUsrId());
        userRepository.save(userEntity);

        if (urlPicture != null) {
            String externalPicturesDir = storageService.getExternalPicturesDir();
            log.info("update urlPicture 2 : " + externalPicturesDir + userEntity.getUsrId() + "_" + urlPicture);
            clientEntity.setCliUrlPicture(externalPicturesDir + userEntity.getUsrId() + "_" + urlPicture);
        }

        clientEntity.setUser(userEntity);
        clientRepository.save(clientEntity);
    }
}