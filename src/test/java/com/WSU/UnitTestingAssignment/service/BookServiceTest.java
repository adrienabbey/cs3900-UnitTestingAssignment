package com.WSU.UnitTestingAssignment.service;

import com.WSU.UnitTestingAssignment.domain.Book;
import com.WSU.UnitTestingAssignment.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    // done: the name of this test could be improved; rename it to be more explicit
    // done: fix this failing test; book is null now that we have a collaborator
    @Test
    public void createBookReturnsAuthorAndTitle() {
        // Create a new book to work with:
        Book newBook = new Book(1, "Some Author", "Some Title");

        // Configure the mockup, which is required before testing:
        when(bookRepository.save(new Book(1, "Some Author", "Some Title")))
                .thenReturn(new Book(1, "Some Author", "Some Title"));

        // Use the function being tested and save the result:
        Book book = bookService.createBook(
                new Book(1, "Some Author", "Some Title"));

        // Actual tests: ensure the results are as expected:
        assertEquals(newBook.getAuthor(), book.getAuthor());
        assertEquals(newBook.getTitle(), book.getTitle());
        assertNotNull(book.getId());
    }

    // done: fix this failing test
    @Test
    public void getBookByIdReturnsSpecifiedBook() {
        when(bookRepository.findById(45)).thenReturn(
            new Book(45, "The Eye of the World", "Robert Jordan")
        );

        Book expectedBook = new Book(45, "The Eye of the World", "Robert Jordan");

        assertEquals(expectedBook, bookService.getBookById(45)); // The ID number was wrong.
    }

    // todo: fix this failing test
    @Test
    public void deleteBookReturnsTrueWhenBookDeleted() {
        when(bookRepository.existsById(any())).thenReturn(false);

        boolean result = bookService.deleteBook(22);

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(bookRepository, times(1)).delete(idCaptor.capture());

        assertTrue(result);
        assertEquals(22, idCaptor.getValue());
    }

    // todo: fix this failing test
    @Test
    public void deleteBookReturnsFalseWhenBookNotDeleted() {
        when(bookRepository.existsById(42)).thenReturn(true);
        assertFalse(bookService.deleteBook(1));
    }

    // todo: write tests for updateBook() to achieve 100% code coverage
    @Test
    public void updateBookReturnsUpdatedBook() {
        // Create a book to test against:
        Book goodBook = new Book(1, "Shirtaloon", "He Who Fights with Monsters");

        // Configure the mockup:
        when(bookRepository.changeBook(new Book(1, "Travis Deverell", "He Who Fights with Monsters"), 1))
                .thenReturn(new Book(1, "Shirtaloon", "He Who Fights with Monsters"));

        // Create a book to update:
        bookService.createBook(new Book(1, "Travis Deverell", "He Who Fights with Monsters"));

        Book testBook = bookService.getBookById(1);
        System.err.println(testBook);

        // Use the function being tested:
        Book updatedBook = bookService.updateBook(new Book(1, "Shirtaloon", "He Who Fights with Monsters"), 1);

        // Verify results:
        assertEquals(goodBook, updatedBook);
    }
}
