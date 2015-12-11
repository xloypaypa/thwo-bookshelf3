package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookShelfController {

    private BookService bookService = new BookService();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelMap model = new ModelMap();
        model.put("books", bookService.findAll());
        return new ModelAndView("books", model);
    }

    @RequestMapping("book/{isbn}")
    public ModelAndView showProduct(@PathVariable String isbn) {
        ModelMap model = new ModelMap();
        model.put("book", BookService.findOne(isbn));
        return new ModelAndView("book", model);
    }

    @RequestMapping("book/edit/{isbn}")
    public ModelAndView edit(@PathVariable String isbn) {
        ModelMap model = new ModelMap();
        model.put("book", BookService.findOne(isbn));
        return new ModelAndView("newBook", model);
    }

    @RequestMapping("book/new")
    public ModelAndView newProduct() {
        ModelMap model = new ModelMap();
        model.put("book", new Book());
        return new ModelAndView("newBook", model);
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String saveProduct(Book book) {
        bookService.save(book);
        return "redirect:/book/" + book.getIsbn();
    }

}
