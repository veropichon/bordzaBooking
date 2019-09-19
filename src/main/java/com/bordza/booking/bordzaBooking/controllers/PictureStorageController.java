package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PictureStorageController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    StorageService storageService;

    private static final Logger log = LoggerFactory.getLogger("test Input");


    @PostMapping("/deletePicture")
    public String deletePicture(@ModelAttribute("modelClient") ClientEntity inputClientEntity,
                                BindingResult result, ModelMap model) {

        ClientEntity clientEntity = clientRepository.findById(inputClientEntity.getCliId()).get();
        storageService.deleteFile(clientEntity.getCliUrlPicture());

        clientEntity.setCliUrlPicture(null);
        clientRepository.save(clientEntity);

        return "redirect:/modifProfil";
    }

    /*@PostMapping("/savePicture")
    public String savePicture(@ModelAttribute("modelClient") ClientEntity inputClientEntity,
                              BindingResult result, ModelMap model,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {


        ClientEntity clientEntity = clientRepository.findById(inputClientEntity.getCliId()).get();

        log.info("id input client : " + inputClientEntity.getCliId());
        log.info("url picture : " + clientEntity.getCliUrlPicture());

        /**
         * Delete file if not null
         */
    /*
        if (clientEntity.getCliUrlPicture() != null) {
            storageService.deleteFile(clientEntity.getCliUrlPicture());
            clientEntity.setCliUrlPicture(null);
            clientRepository.save(clientEntity);
        }

        //Save picture
        String urlPicture = storageService.store(file, redirectAttributes);
        clientEntity.setCliUrlPicture(urlPicture);

        return "redirect:/modifProfil";
    }*/


}
