package com.learn.controller.admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerForAdmin")
public class HomeController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/admin");
        return mav;
    }

}