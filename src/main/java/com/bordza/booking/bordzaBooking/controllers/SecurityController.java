package com.bordza.booking.bordzaBooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            if (error.equals("needlog")) {
                ModelAndView mav = new ModelAndView("login");
                mav.addObject("error", "Veuillez vous connecter pour accéder à cette page");
                return mav;
            } else if (error.equals("wronglog")) {
                ModelAndView mav = new ModelAndView("login");
                mav.addObject("error", "Email/Mot de passe Incorrect");
                return mav;
            }
        }

        return new ModelAndView("login");
    }
}
