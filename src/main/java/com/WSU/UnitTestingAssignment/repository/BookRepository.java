package com.WSU.UnitTestingAssignment.repository;

import com.WSU.UnitTestingAssignment.domain.Book;
import java.util.Random;

public class BookRepository {
    public Book save(Book book) {
        Random random = new Random();
        book.setId(random.nextInt(1234));
        return book;
    }

    public boolean existsById(Integer id) {
        Random random = new Random();
        return random.nextBoolean();
    }

    public Book findById(Integer id) {
        return existsById(id) ? get(id) : null;
    }

    public boolean delete(Integer id) {
        Random random = new Random();
        return random.nextBoolean();
    }

    public Book get(Integer id) {
        return new Book(id, "author", "title");
    }
}
