package com.thoughtworks.jimmy.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.thoughtworks.jimmy.enumeration.Color;
import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.model.SelfTag;
import com.thoughtworks.jimmy.repository.BookRepository;
import com.thoughtworks.jimmy.repository.SelfTagRepository;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private SelfTagRepository selfTagRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_find_book_by_title_when_given_title() {
        Iterable<Book> expectedBooks = Arrays.asList(
                new Book("12345", "Head Fist Java", "author", 55.0)
        );
        String title = "Java";
        when(bookRepository.findByTitle(title)).thenReturn(expectedBooks);

        Iterable<Book> books = bookService.findByTitle(title);

        assertEquals(expectedBooks, books);

    }

    @Test
    public void should_find_book_by_tag_when_given_valid_tag() {
        Iterable<Book> expectedBooks = Arrays.asList();
        SelfTag selfTag = new SelfTag(1, "IT", Color.PURPLE);
        when(selfTagRepository.findByTag(selfTag.getTag())).thenReturn(selfTag);
        when(bookRepository.findByTagId(selfTag.getId())).thenReturn(expectedBooks);

        Iterable<Book> books = bookService.findByTag(selfTag.getTag());

        assertEquals(expectedBooks, books);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_exception_when_tag_not_found() {
        String tag = "tag";
        when(selfTagRepository.findByTag(tag)).thenReturn(null);

        bookService.findByTag(tag);
    }
}