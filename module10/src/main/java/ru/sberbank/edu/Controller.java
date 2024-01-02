package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RequestMapping(value = "/", produces = "text/html; charset=UTF-8")
@PropertySource("classpath:app.properties")
public class Controller {
    @Value("${sum}")
    private int minOpeningAmount;

    @GetMapping(path = "/finance")
    public ModelAndView info() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/base.jsp");
        return modelAndView;
    }

    @PostMapping(path = "/finance")
    public ModelAndView postForm(@RequestParam("sum") int sum,
                                 @RequestParam("percentage") double percentage,
                                 @RequestParam("years") int years) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (sum < minOpeningAmount) {
                modelAndView.addObject("error", "Минимальная сумма на момент открытия вклада " + minOpeningAmount + " рублей");
                modelAndView.setViewName("/minSum.jsp");
                return modelAndView;
            } else if (sum < 0 || percentage < 0 || years < 0) {
                modelAndView.setViewName("/error.jsp");
            } else {
                double r = percentage / 100;
                int n = 1;
                String futureValue = String.valueOf((int) (sum * Math.pow(1 + r / n, n * years)));
                modelAndView.addObject("futureValue", futureValue);
                modelAndView.setViewName("/result.jsp");
                return modelAndView;
            }
        } catch (NumberFormatException ex) {
            modelAndView.setViewName("/error.jsp");
        }
        return modelAndView;
    }
}
