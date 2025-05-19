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

    public boolean borrowBook(int bookId){
        Book book = bookStore.getOrDefault(bookId, null);
        if(book==null || book.getCopiesAvailable()<=0){
            return false;
        }
        book.setCopiesAvailable(book.getCopiesAvailable()-1);
        System.out.println("Book "+book.getBookId()+" borrowed successfully!");
        return true;
    }

    public boolean returnBook(int bookId){
        Book book = bookStore.getOrDefault(bookId, null);
        if(book==null){
            return false;
        }
        book.setCopiesAvailable(book.getCopiesAvailable()+1);
        System.out.println("Book "+book.getBookId()+" returned successfully!");
        return true;
    }

    public Set<Book> fetchAllBooks(){
        return new HashSet<>(bookStore.values());
    }

    public Set<Book> searchBooksByTitle(String title){
        Set<Book> books = new HashSet<>();
        for(Book book: bookStore.values()){
            if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
                books.add(book);
            }
        }
        return books;
    }

    public Set<Book> searchBooksByAuthor(String author){
        Set<Book> books = new HashSet<>();
        for(Book book: bookStore.values()){
            if(book.getAuthor().toLowerCase().contains(author.toLowerCase())){
                books.add(book);
            }
        }
        return books;
    }

}
