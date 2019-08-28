package com.bordza.booking.bordzaBooking.controllers;


import com.bordza.booking.bordzaBooking.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bordza.booking.bordzaBooking.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger("test Input");

    private static List<UserEntity> usersMap = new ArrayList<>();

    static {
        usersMap.add(new UserEntity("Bill", "Gates"));
        usersMap.add(new UserEntity("Steve", "Jobsrr"));
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("usersMap", usersMap);

        return "index";
    }

    @GetMapping("/signup")
    //@RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute("inputUser", new UserEntity());

        return "signup";
    }

    //@PostMapping("/signup")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("inputUser") UserEntity userEntity,
                           BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "error";
        }

        String usrLogin = userEntity.getUsrLogin();
        String usrPwd = userEntity.getUsrPwd();

        if (usrLogin != null && usrLogin.length() > 0 //
                && usrPwd != null && usrPwd.length() > 0) {
            //usersMap.add(new UserEntity(usrLogin, usrPwd));
            userRepository.save(userEntity);
            return "redirect:/index";
        }
        /*
        String usrLogin = userEntity.getUsrLogin();
        String usrPwd = userEntity.getUsrPwd();

        log.info("login : " + usrLogin + " / pwd : " + usrPwd);

        if (usrLogin != null && usrLogin.length() > 0 //
                && usrPwd != null && usrPwd.length() > 0) {
            usersMap.add(new UserEntity(usrLogin, usrPwd));

            return "redirect:/index";
        }*/
        return "signup";
    }
}