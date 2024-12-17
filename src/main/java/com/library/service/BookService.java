package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookService {

    private static final Logger logger = Logger.getLogger(BookService.class.getName());
    private final BookDAO bookDAO;
    private List<Book> books;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.books = bookDAO.getAllBooks();
    }

    public void addBook(Book book) {
        try {
            bookDAO.addBook(book);
            logger.info("Book added successfully: " + book.getTitle());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding book: " + e.getMessage(), e);
        }
    }

    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public void getAllBooks() {
        if (books.isEmpty()) {
            logger.warning("No books available.");
        } else {
            for (Book book : books) {
                logger.info("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }

    public void updateBook(Book book) {
        try {
            bookDAO.updateBook(book);
            logger.info("Book updated successfully: " + book.getTitle());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating book: " + e.getMessage(), e);
        }
    }

    public void deleteBook(int id) {
        try {
            bookDAO.deleteBook(id);
            logger.info("Book deleted successfully, ID: " + id);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting book: " + e.getMessage(), e);
        }
    }

    public Book findBookByTitle(String title) {
        return bookDAO.findBookByTitle(title);
    }
}
