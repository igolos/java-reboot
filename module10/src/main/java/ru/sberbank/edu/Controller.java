package ru.sberbank.edu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/")
public class Controller {

    @GetMapping(path = "/finance")
    public ModelAndView info(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/ru.jsp");
        return modelAndView;
    }
}
