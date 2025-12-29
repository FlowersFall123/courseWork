package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @GetMapping("/practice")
    public String practice(Model model) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++){
                list.add(i+"*"+j+"="+i*j+" ");
            }
            list.add("<br>");
        }
        model.addAttribute("list", list);
        return "Practice";
    }
}
