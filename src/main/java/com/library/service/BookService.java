package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookDAO bookDAO;
    private List<Book> books;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.books = bookDAO.getAllBooks(); 
    }

    public void addBook(Book book) {
        bookDAO.addBook(book);
        logger.info("Book added: {}", book.getTitle());
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        Book book = bookDAO.getBookByIsbn(isbn);
        return Optional.ofNullable(book);
    }

    public Optional<Book> getBookById(int id) {
        Book book = bookDAO.getBookById(id);
        return Optional.ofNullable(book);
    }

    public void getAllBooks() {
        if (books.isEmpty()) {
            logger.warn("No books available.");
        } else {
            for (Book book : books) {
                logger.info("Title: {}, Author: {}", book.getTitle(), book.getAuthor());
            }
        }
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
        logger.info("Book updated: {}", book.getTitle());
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
        logger.info("Book deleted with ID: {}", id);
    }

    public Optional<Book> findBookByTitle(String title) {
        Book book = bookDAO.findBookByTitle(title);
        return Optional.ofNullable(book);
    }

}
