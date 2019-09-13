package com.bordza.booking.bordzaBooking.utils;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

@Component
public class ClientValidator {

    public void clientValidator(ClientEntity clientEntity, UserEntity userEntity) {

        String cliFirstname = clientEntity.getCliFirstname();
        String cliLastname = clientEntity.getCliLastname();
        String cliPhone = clientEntity.getCliPhone();

        String cliZipcode = clientEntity.getCliZipcode();
        String cliCity = clientEntity.getCliCity();
        Integer cliWeight = clientEntity.getCliWeight();
        Integer cliHeight = clientEntity.getCliHeight();
        String cliTutorFirstname = clientEntity.getCliTutorFirstname();
        String cliTutorLastname = clientEntity.getCliTutorLastname();
        String cliTutorEmail = clientEntity.getCliTutorEmail();
        String cliTutorPhone = clientEntity.getCliTutorPhone();
        String cliComment = clientEntity.getCliComment();

        String usrEmail = StringUtils.trim(userEntity.getUsrEmail());
        String usrPwd = userEntity.getUsrPwd();

        EmailValidator emailValidator = EmailValidator.getInstance();

        /**
         * User Validator
         */
        if (StringUtils.isBlank(usrEmail) && !emailValidator.isValid(usrEmail) && usrEmail.length() > 50) {
            throw new IllegalArgumentException("Votre email n'est pas valide");
        }

        if (StringUtils.isEmpty(usrPwd) && usrEmail.length() <= 8 && usrEmail.length() > 50) {
            throw new IllegalArgumentException("Le mot de passe n'est pas valide. Vous devez utiliser au moins minimum 6 lettres, une majuscule et un nombre");
        }

        /**
         * Client validator
         */
        if (StringUtils.isBlank((cliFirstname)) && !PatternMatchUtils.simpleMatch("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", cliFirstname) && cliFirstname.length() > 50) {
            throw new IllegalArgumentException("Le Prénom ne peut pas contenir de chiffre");
        }

        if (StringUtils.isBlank((cliLastname)) && !PatternMatchUtils.simpleMatch("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", cliLastname) && cliLastname.length() > 50) {
            throw new IllegalArgumentException("le nom ne peut pas contenir de chiffre");
        }

        if (StringUtils.isBlank((cliPhone)) && !PatternMatchUtils.simpleMatch("([0-9]{2}){5}", cliPhone) && cliPhone.length() > 50) {
            throw new IllegalArgumentException("Le numéro de téléphone n'est pas valide");
        }

        if (!PatternMatchUtils.simpleMatch("[0-9]{5}", cliZipcode) && cliZipcode.length() > 50) {
            throw new IllegalArgumentException("Le code postale n'est pas valide");
        }

        if (!PatternMatchUtils.simpleMatch("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", cliCity) && cliCity.length() > 50) {
            throw new IllegalArgumentException("Le code postale n'est pas valide");
        }

        if (cliHeight != null) {
            if (!PatternMatchUtils.simpleMatch("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", cliHeight.toString()) && cliHeight.toString().length() > 3) {
                throw new IllegalArgumentException("La taille n'est pas valide");
            }
        }

        if (cliWeight != null) {
            if (!PatternMatchUtils.simpleMatch("\\d*", cliWeight.toString()) && cliWeight.toString().length() > 3) {
                throw new IllegalArgumentException("Le poids n'est pas valide");
            }
        }

        if (!PatternMatchUtils.simpleMatch("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", cliTutorFirstname) && cliTutorFirstname.length() > 50) {
            throw new IllegalArgumentException("Le Prénom ne peut pas contenir de chiffre");
        }

        if (!PatternMatchUtils.simpleMatch("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", cliTutorLastname) && cliTutorLastname.length() > 50) {
            throw new IllegalArgumentException("Le nom ne peut pas contenir de chiffre");
        }
        if (cliTutorEmail != null) {
            if (!emailValidator.isValid(cliTutorEmail) && cliTutorEmail.length() > 50) {
                throw new IllegalArgumentException("L'email du tuteur n'est pas valide");
            }
        }

        if (StringUtils.isBlank((cliTutorPhone)) && !PatternMatchUtils.simpleMatch("([0-9]{2}){5}", cliTutorPhone) && cliTutorPhone.length() > 50) {
            throw new IllegalArgumentException("Le numéro de téléphone n'est pas valide");
        }

        if (cliComment.length() > 255) {
            throw new IllegalArgumentException("Le nom ne peut pas contenir de chiffre");
        }
    }
}
