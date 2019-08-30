package com.bordza.booking.bordzaBooking.controllers;


import com.bordza.booking.bordzaBooking.domain.ClientEntity;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import com.bordza.booking.bordzaBooking.repositories.ClientRepository;
import com.bordza.booking.bordzaBooking.repositories.UserRepository;
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
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    private static final Logger log = LoggerFactory.getLogger("test Input");


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        List<UserEntity> usersMap = userRepository.findAll();
        model.addAttribute("usersMap", usersMap);

        return "index";
    }

    @GetMapping("/inscription")
    public String inscription(Model model) {

        model.addAttribute("inputUser", new UserEntity());
        model.addAttribute("inputClient", new ClientEntity());

        return "inscription";
    }

    @PostMapping("/inscription")
    public String saveUser(@ModelAttribute("inputUser") UserEntity userEntity,
                           BindingResult result, ModelMap model) {


        if (result.hasErrors()) {
            return "error";
        }


        String usrLogin = userEntity.getUsrLogin();
        String usrPwd = userEntity.getUsrPwd();

        if (usrLogin != null && usrLogin.length() > 0
                && usrPwd != null && usrPwd.length() > 0) {
            userRepository.save(userEntity);
            return "redirect:/index";
        }

        return "inscription";
    }
}