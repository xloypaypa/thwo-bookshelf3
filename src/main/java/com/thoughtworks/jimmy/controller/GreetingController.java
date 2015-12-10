package com.thoughtworks.jimmy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {

    @RequestMapping("/hello/{name}")
    public String greeting(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

}
