package com.thoughtworks.jimmy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.thoughtworks.jimmy.model.Book;

public interface BookRepository extends CrudRepository<Book, String>{
    @Query("SELECT b FROM Book b WHERE b.title like %?1%")
    Iterable<Book> findByTitle(String title);
}
