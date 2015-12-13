package com.thoughtworks.jimmy.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.thoughtworks.jimmy.SpringBootWebApplicationTests;
import com.thoughtworks.jimmy.model.Book;

public class BookRepositoryTest extends SpringBootWebApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void should_find_one_book_by_isbn_when_book_exists() {
        Book book = new Book("9780201485672", "name", "author", 13.5);
        bookRepository.save(book);

        Book fetchBook = bookRepository.findOne(book.getIsbn());

        assertEquals(book.getIsbn(), fetchBook.getIsbn());
        assertEquals(book.getName(), fetchBook.getName());
        assertEquals(book.getAuthor(), fetchBook.getAuthor());
        assertEquals(book.getPrice(), fetchBook.getPrice());

        bookRepository.delete(fetchBook);
    }

    @Test
    public void should_not_find_book_when_book_isbn_not_exists() {
        String isbn = "9780132350884";

        Book book = bookRepository.findOne(isbn);

        assertNull(book);
    }


}