package com.thoughtworks.jimmy.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.thoughtworks.jimmy.model.Book;
import com.thoughtworks.jimmy.repository.BookRepository;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_find_book_by_title_when_given_title() {
        Iterable<Book> expectedBooks = Arrays.asList(
                new Book("Head Fist Java", "title", "author", 55.0)
        );
        String title = "Java";
        when(bookRepository.findByTitle(title)).thenReturn(expectedBooks);

        Iterable<Book> books = bookService.findByTitle(title);

        assertEquals(expectedBooks, books);

    }
}