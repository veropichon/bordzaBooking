package com.bordza.booking.bordzaBooking.controllers;


import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.LevelEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.LevelRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import com.bordza.booking.bordzaBooking.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        return "inscription";
    }

    //Send lvl, User and Client model to "Inscription"
    @GetMapping("/modifProfil")
    public String viewProfil(Model model) {

        List<LevelEntity> levelsList = levelRepository.findAll();
        model.addAttribute("modelLevel", levelsList);

        ClientEntity clientEntity = clientRepository.findById(4L).get();
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
                                    BindingResult result, ModelMap model) {

        /*if (result.hasErrors()) {
            return "error";
        }*/

        // Add defeult values
        userEntity.defaultValue(userEntity);
        clientEntity.defaultValue(clientEntity);

        try {
            clientService.save(userEntity, clientEntity);
        } catch (IllegalArgumentException e) {


            return "inscription";
        }
        return "redirect:/index";
    }

    @PostMapping("/modifProfil")
    public String updateProfil(@ModelAttribute("modelUser") UserEntity userEntity,
                               @ModelAttribute("modelClient") ClientEntity clientEntity,
                               BindingResult result, ModelMap model) {

        /*if (result.hasErrors()) {
            return "error";
        }*/

        // Add defeult values
        userEntity.defaultValue(userEntity);
        clientEntity.defaultValue(clientEntity);

        try {
            clientService.update(userEntity, clientEntity);
        } catch (IllegalArgumentException e) {


            return "inscription";
        }
        return "redirect:/index";
    }

}

