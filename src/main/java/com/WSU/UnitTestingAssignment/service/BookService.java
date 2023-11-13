package com.WSU.UnitTestingAssignment.service;

import com.WSU.UnitTestingAssignment.domain.Book;
import com.WSU.UnitTestingAssignment.exception.ResourceNotFoundException;
import com.WSU.UnitTestingAssignment.repository.BookRepository;

public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public Book getBookById(Integer id) {
        return repository.findById(id);
    }

    public boolean deleteBook(Integer id) {
        if(repository.existsById(id)) {
            repository.delete(id);
            return true;
        } else {
            return false;
        }
    }

    public Book updateBook(Book updatedBook, Integer id) {
        if (repository.existsById(id)) {
            Book book = repository.get(id);
            book.setAuthor(updatedBook.getAuthor());
            book.setTitle(updatedBook.getTitle());
            return repository.save(book);
        }

        throw new ResourceNotFoundException();
    }
}
