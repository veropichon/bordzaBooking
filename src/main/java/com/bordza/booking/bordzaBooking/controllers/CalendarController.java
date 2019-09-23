package com.bordza.booking.bordzaBooking.controllers;

import com.bordza.booking.bordzaBooking.repositories.CourseClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {


    // @RequestMapping(value="/", method= RequestMethod.GET)
    // public ModelAndView index() {
        // return new ModelAndView("index");
    // }

    @Autowired
    CourseClientRepository courseClientRepository;


    @RequestMapping(value="/staticcalendar", method=RequestMethod.GET)
    public ModelAndView staticcalendar() {
        return new ModelAndView("staticcalendar");
    }

    @RequestMapping(value="/calendar", method=RequestMethod.GET)
    public ModelAndView calendar() {


        return new ModelAndView("calendar");
    }

    @RequestMapping(value="/admincalendar", method=RequestMethod.GET)
    public ModelAndView admincalendar() {


        //model.addAttribute("bookingToValid", courseClientRepository.findAllByBkValidated(false).size());

        ModelAndView mav = new ModelAndView("admincalendar");
        mav.addObject("bookingToValid", courseClientRepository.findAllByBkValidated(false).size());
        return mav;

        //return new ModelAndView("admincalendar");
    }

    @RequestMapping(value="/jsoncalendar", method=RequestMethod.GET)
    public ModelAndView jsoncalendar() {
        return new ModelAndView("jsoncalendar");
    }


}