package com.bordza.booking.bordzaBooking.services;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

import java.sql.Date;


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

        String cliFirstname = StringUtils.trim(clientEntity.getCliFirstname());
        String cliLastname = StringUtils.trim(clientEntity.getCliLastname());
        String cliPhone = StringUtils.trim(clientEntity.getCliPhone());
        Integer cliZipcode = clientEntity.getCliZipcode();
        String cliCity = StringUtils.trim(clientEntity.getCliCity());
        Integer cliWeight = clientEntity.getCliWeight();
        Integer cliHeight = clientEntity.getCliHeight();
        Date cliBirthdate = clientEntity.getCliBirthdate();
        String cliTutorFirstname = StringUtils.trim(clientEntity.getCliTutorFirstname());
        String cliTutorLastname = StringUtils.trim(clientEntity.getCliTutorLastname());
        String cliTutorEmail = StringUtils.trim(clientEntity.getCliTutorEmail());
        String cliTutorPhone = StringUtils.trim(clientEntity.getCliTutorPhone());
        String cliComment = StringUtils.trim(clientEntity.getCliComment());
        Long clientlvlId = clientEntity.getLevel().getLevId();

        String usrEmail = StringUtils.trim(userEntity.getUsrEmail());
        String usrPwd = userEntity.getUsrPwd();

        /*final String Emailregex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        boolean PatternEmail = PatternMatchUtils.simpleMatch(Emailregex, usrEmail);

        final String PwdRegex = "((?=.*[a-z])(?=.*d)(?=.*[A-Z]).{6,16})";
        boolean PatternPwd = PatternMatchUtils.simpleMatch(PwdRegex, usrPwd);

        EmailValidator emailValidator = EmailValidator.getInstance();
        valid = emailValidator.isValid("sandeep_giet@yahoo.com");

        if (StringUtils.isBlank(usrEmail) && !PatternEmail && usrEmail.length() <= 6) {
            throw new IllegalArgumentException("Votre email n'est pas valide");
        }

        if(StringUtils.isEmpty(usrPwd) && !PatternPwd) {
           throw new IllegalArgumentException("Le mot de passe n'est pas valide. Vous devez utiliser au moins minimum 6 lettres, une majuscule et un nombre");
        }

        if (StringUtils.isBlank((cliFirstname)) && PatternMatchUtils.simpleMatch("(\\d*)", cliFirstname)){
            throw new  IllegalArgumentException("Votre Prénom ne peut pas contenir de chiffre");
        }

        if (StringUtils.isBlank((cliLastname)) && PatternMatchUtils.simpleMatch("\\d*", cliLastname)){
            throw new  IllegalArgumentException("Votre nom ne peut pas contenir de chiffre");
        }

        if (StringUtils.isBlank((cliPhone)) && PatternMatchUtils.simpleMatch("([0-9]{2}){5}", cliLastname)){
            throw new  IllegalArgumentException("Votre nom ne peut pas contenir de chiffre");
        }*/


        // TODO verif!

        log.info("User : " + userEntity.toString());
        log.info("Client : " + clientEntity.toString());

        userRepository.save(userEntity);

        clientEntity.setUser(userEntity);
        clientRepository.save(clientEntity);
    }
}
