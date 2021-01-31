package com.learn.controller.admin;

import com.learn.model.NewsModel;
import com.learn.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "homeControllerForAdmin")
public class HomeController {
    @Autowired
    private INewsService newsService;
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/admin");
        return mav;
    }
    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView editNews(){
        ModelAndView mav = new ModelAndView("admin/news/edit");
        return mav;
    }
    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public ModelAndView listNews(){
        ModelAndView mav = new ModelAndView("admin/news/list");
        List<NewsModel> listModel = newsService.findAll();
        mav.addObject("listNews",listModel);
        return mav;
    }
}