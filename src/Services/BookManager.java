package Services;

import entities.Book;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookManager {
    //list of books
    //add, update, view and search books
    Map<Integer, Book> bookStore = new HashMap<>();
    int bookIdSequence = 1;

    public Book addBook(String title, String description, String author, int copies){
        Book book = new Book(bookIdSequence++, title, description, author, copies);
        bookStore.put(bookIdSequence-1, book);
        System.out.println("New book added successfully!");
        return book;
    }

    public Book updateBook(int bookId, String title, String description, String author, int copies){
        Book book = bookStore.get(bookId);
        if(author!=null)book.setAuthor(author);
        if(description!=null)book.setDescription(description);
        if(title!=null)book.setTitle(title);
        if(copies>=0)book.setCopiesAvailable(copies);
        System.out.println("Book details updated successfully!");
        return book;
    }

    public Set<Book> fetchAllBooks(){
        return (Set<Book>) bookStore.values();
    }

    public Set<Book> searchBooksByTitle(String title){
        Set<Book> books = new HashSet<>();
        for(Book book: bookStore.values()){
            if(book.getTitle().contains(title)){
                books.add(book);
            }
        }
        return books;
    }

    public Set<Book> searchBooksByAuthor(String author){
        Set<Book> books = new HashSet<>();
        for(Book book: bookStore.values()){
            if(book.getAuthor().contains(author)){
                books.add(book);
            }
        }
        return books;
    }

}
