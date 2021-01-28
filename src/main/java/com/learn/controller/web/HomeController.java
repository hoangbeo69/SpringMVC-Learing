package com.learn.controller.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerForWeb")
public class HomeController {

    @RequestMapping(value = "/web", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("web/home");
        return mav;
    }
}