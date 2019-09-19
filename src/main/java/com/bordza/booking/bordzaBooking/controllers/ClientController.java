package com.bordza.booking.bordzaBooking.controllers;


import com.bordza.booking.bordzaBooking.domain.*;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.LevelRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import com.bordza.booking.bordzaBooking.services.ClientService;
import com.bordza.booking.bordzaBooking.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ClientController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LevelRepository levelRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    StorageService storageService;

    private static final Logger log = LoggerFactory.getLogger("test Input");

    // Show user and client table on page index
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        List<UserEntity> usersList = userRepository.findAll();
        model.addAttribute("usersList", usersList);

        List<ClientEntity> clientsList = clientRepository.findAll();
        model.addAttribute("clientsList", clientsList);

        model.addAttribute("pageTitle", "Accueil");

        return "index";
    }

    @GetMapping("/clientProfil")
    public String clientProfil(Model model, @RequestParam String clientId){

        ClientEntity clientEntity = clientRepository.findById(Long.valueOf(clientId)).get();

        model.addAttribute("clientProfil", clientEntity);
        model.addAttribute("pageTitle", "Profil");

        clientEntity.getCliBirthdate();

        return "clientProfil";
    }



    //Send lvl, User and Client model to "Inscription"
    @GetMapping("/inscription")
    public String inscription(Model model) {

        List<LevelEntity> levelsList = levelRepository.findAll();
        model.addAttribute("modelLevel", levelsList);

        LevelEntity level = levelRepository.findById(1L).get();
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setLevel(level);

        model.addAttribute("modelUser", new UserEntity());
        model.addAttribute("modelClient", clientEntity);
        model.addAttribute("pageTitle", "Inscription");
        model.addAttribute("formType", "inscription");
        model.addAttribute("cliAge", new CliAge());

        return "inscription";
    }

    //Send lvl, User and Client model to "Inscription"
    @GetMapping("/modifProfil")
    public String viewProfil(Model model) {

        List<LevelEntity> levelsList = levelRepository.findAll();
        model.addAttribute("modelLevel", levelsList);

        ClientEntity clientEntity = clientRepository.findById(1L).get();
        UserEntity userEntity = userRepository.findById(clientEntity.getUser().getUsrId()).get();

        model.addAttribute("modelUser", userEntity);
        model.addAttribute("modelClient", clientEntity);
        model.addAttribute("pageTitle", "Mon profil");
        model.addAttribute("formType", "modifProfil");

        return "inscription";
    }

    //Save User and Client
    @PostMapping("/inscription")
    public String saveUserAndClient(@ModelAttribute("modelUser") UserEntity userEntity,
                                    @ModelAttribute("modelClient") ClientEntity clientEntity,
                                    BindingResult result, ModelMap model,
                                    @RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) {

        /*if (result.hasErrors()) {
            return "error";
        }*/

        log.info(file.getOriginalFilename());

        List<UserEntity> EmailsList = userRepository.findByUsrEmailContaining(userEntity.getUsrEmail());

        if (EmailsList.isEmpty()) {
            // Add defeult values
            userEntity.defaultValue(userEntity);
            clientEntity.defaultValue(clientEntity);

            //Save picture

            try {
                clientService.save(userEntity, clientEntity, file, redirectAttributes);
            } catch (IllegalArgumentException e) {
                return "inscription";
            }

            String url = "redirect:/clientProfil?clientId=" + String.valueOf(clientEntity.getCliId());
            return url;

            //return "redirect:/calendar";
        } else {
            return "redirect:/_error";
        }

    }

    @PostMapping("/modifProfil")
    public String updateProfil(@ModelAttribute("modelUser") UserEntity userEntity,
                               @ModelAttribute("modelClient") ClientEntity inputClientEntity,
                               BindingResult result, ModelMap model,
                               @RequestParam("file") MultipartFile file,
                               RedirectAttributes redirectAttributes) {

        /*if (result.hasErrors()) {
            return "error";
        }*/

        // Add defeult values
        userEntity.defaultValue(userEntity);
        inputClientEntity.defaultValue(inputClientEntity);

        ClientEntity clientEntity = clientRepository.findById(inputClientEntity.getCliId()).get();

        log.info("id input client : " + inputClientEntity.getCliId());

        if (clientEntity.getCliUrlPicture() != null) {
            storageService.deleteFile(clientEntity.getCliUrlPicture());
            clientEntity.setCliUrlPicture(null);
            clientRepository.save(clientEntity);
        }

        //Save picture
        String urlPicture = storageService.store(file, redirectAttributes, clientEntity.getUser());

        try {
            clientService.update(userEntity, inputClientEntity, urlPicture);
        } catch (IllegalArgumentException e) {
            return "inscription";
        }

        log.info("avant profil client : " + clientEntity.getCliId());

        String url = "redirect:/clientProfil?clientId=" + String.valueOf(clientEntity.getCliId());
        return url;
        //return "redirect:/index";
    }

}

