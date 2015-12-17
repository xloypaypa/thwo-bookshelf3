package com.thoughtworks.jimmy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.model.SelfTag;
import com.thoughtworks.jimmy.repository.BookRepository;
import com.thoughtworks.jimmy.repository.SelfTagRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SelfTagRepository selfTagRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findOne(isbn);
    }

    @Override
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(String isbn) {
        bookRepository.delete(isbn);
    }

    @Override
    public void edit(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Iterable<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Iterable<Book> findByTag(String tag) {
        SelfTag selfTag = selfTagRepository.findByTag(tag);
        Optional.ofNullable(selfTag).orElseThrow(() -> new RuntimeException("Tag not found !"));
        return bookRepository.findByTagId(selfTag.getId());
    }

}
