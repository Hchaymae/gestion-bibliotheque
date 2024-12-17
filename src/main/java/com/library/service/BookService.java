package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;
import java.util.List;

public class BookService {

    private final BookDAO bookDAO;
    private List<Book> books;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.books = bookDAO.getAllBooks();
    }

    public void addBook(Book book) {
        try {
            bookDAO.addBook(book);
            System.out.println("Book added successfully: " + book.getTitle());
        } catch (Exception e) {
            System.err.println("Error adding book: " + e.getMessage());
            e.printStackTrace();
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
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }

    public void updateBook(Book book) {
        try {
            bookDAO.updateBook(book);
            System.out.println("Book updated successfully: " + book.getTitle());
        } catch (Exception e) {
            System.err.println("Error updating book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try {
            bookDAO.deleteBook(id);
            System.out.println("Book deleted successfully, ID: " + id);
        } catch (Exception e) {
            System.err.println("Error deleting book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Book findBookByTitle(String title) {
        return bookDAO.findBookByTitle(title);
    }
}