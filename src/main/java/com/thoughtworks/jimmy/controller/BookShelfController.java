package com.thoughtworks.jimmy.controller;

import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookShelfController {

    private BookService bookService = new BookService();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @RequestMapping("book/{isbn}")
    public String showProduct(@PathVariable String isbn, Model model){
        model.addAttribute("book", BookService.findOne(isbn));
        return "book";
    }

    @RequestMapping("book/edit/{isbn}")
    public String edit(@PathVariable String isbn, Model model){
        model.addAttribute("book", BookService.findOne(isbn));
        return "newBook";
    }

    @RequestMapping("book/new")
    public String newProduct(Model model){
        model.addAttribute("book", new Book());
        return "newBook";
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String saveProduct(Book book){

        bookService.save(book);
        return "redirect:/book/" + book.getIsbn();
    }

}
