package com.thoughtworks.jimmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.service.BookService;

@RestController
@RequestMapping("/books")
public class BookShelfController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET, value = "title/{title}")
    public Iterable<Book> queryByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Book> query() {

        return bookService.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Book book) {

        bookService.create(book);

    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.GET)
    public Book get(@PathVariable String isbn) {

        return bookService.findByIsbn(isbn);

    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String isbn, @RequestBody Book book) {

        if (isbn.equals(book.getIsbn())) {
            bookService.edit(book);
        }

    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {

        bookService.delete(isbn);

    }

}
