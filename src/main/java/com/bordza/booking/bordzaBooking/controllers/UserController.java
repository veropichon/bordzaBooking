package com.bordza.booking.bordzaBooking.controllers;


import com.bordza.booking.bordzaBooking.domain.TestEntity;
import com.bordza.booking.bordzaBooking.repositories.TestRepository;
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

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRepository testRepository;

    private static final Logger log = LoggerFactory.getLogger("test Input");


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        List<UserEntity> usersMap = userRepository.findAll();
        model.addAttribute("usersMap", usersMap);

        List<TestEntity> testsMap = testRepository.findAll();
        model.addAttribute("testsMap", testsMap);

        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("inputUser", new UserEntity());
        model.addAttribute("inputTest", new TestEntity());

        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute("inputUser") UserEntity userEntity,
                           @ModelAttribute("inputTest") TestEntity testEntity,
                           BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "error";
        }


        String usrLogin = userEntity.getUsrLogin();
        String usrPwd = userEntity.getUsrPwd();

        if (usrLogin != null && usrLogin.length() > 0
                && usrPwd != null && usrPwd.length() > 0) {
            userRepository.save(userEntity);
            testRepository.save(testEntity);
            return "redirect:/index";
        }

        return "signup";
    }
}