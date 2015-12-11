package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookShelfController {

    private BookRepository bookRepository = new BookRepository();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView queryBooks() {

        ModelMap model = new ModelMap();
        model.put("books", bookRepository.findAll());
        return new ModelAndView("books", model);

    }

}
