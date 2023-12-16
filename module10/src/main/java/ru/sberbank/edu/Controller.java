package ru.sberbank.edu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/get")
public class Controller {

    @GetMapping
    public ModelAndView info(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/ru.jsp");
        return modelAndView;
    }
}
