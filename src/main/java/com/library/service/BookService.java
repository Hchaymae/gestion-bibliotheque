package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final BookDAO bookDAO;
    private List<Book> books;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        this.books= bookDAO.getAllBooks();
    }

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public void getAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Aucun livre disponible.");
        } else {
            for (Book book : books) {
                System.out.println("Titre: " + book.getTitle() + ", Auteur: " + book.getAuthor());
            }
        }
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    public Book findBookByTitle(String title) {
        return bookDAO.findBookByTitle(title);
    }
}
