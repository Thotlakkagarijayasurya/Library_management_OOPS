package test.services.java;

import main.java.entities.Book;
import main.java.Services.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        bookManager = new BookManager();
    }

    @Test
    void testAddBook() {
        Book book = bookManager.addBook("Java", "Learn Java", "Author A", 3);
        assertNotNull(book);
        assertEquals("Java", book.getTitle());
    }

    @Test
    void testBorrowBook() {
        Book book = bookManager.addBook("Java", "Learn Java", "Author A", 2);
        assertTrue(bookManager.borrowBook(book.getBookId()));
        assertEquals(1, book.getCopiesAvailable());
    }

    @Test
    void testReturnBook() {
        Book book = bookManager.addBook("Java", "Learn Java", "Author A", 2);
        bookManager.borrowBook(book.getBookId());
        assertEquals(1, book.getCopiesAvailable());
        assertTrue(bookManager.returnBook(book.getBookId()));
        assertEquals(2, book.getCopiesAvailable());
    }

    @Test
    void testSearchBooksByTitle() {
        bookManager.addBook("Java", "Learn Java", "Author A", 2);
        Set<Book> result = bookManager.searchBooksByTitle("Java");
        assertFalse(result.isEmpty());
    }
    @Test
    void testSearchBooksByAuthor() {
        bookManager.addBook("Java", "Learn Java", "Author A", 2);
        Set<Book> result = bookManager.searchBooksByAuthor("Author");
        assertFalse(result.isEmpty());
    }

}
