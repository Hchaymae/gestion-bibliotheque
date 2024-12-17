package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;

import java.util.List;

public class BorrowService {

    private final BorrowDAO borrowDAO;
    private BookDAO bookDAO;
    private StudentDAO studentDAO;

    public BorrowService(BorrowDAO borrowDAO, BookDAO bookDAO, StudentDAO studentDAO) {
        this.borrowDAO = borrowDAO;
        this.bookDAO = bookDAO;
        this.studentDAO = studentDAO;
    }

    public List<Borrow> getAllBorrows() {
        return borrowDAO.getAllBorrows();
    }

    public String addBorrow(Borrow borrow) {
        Book book = bookDAO.getBookById(borrow.getBook().getId());

        if (borrow.getStudent() == null || borrow.getBook() == null) {
            System.out.println("Étudiant ou livre non trouvé.");
            return "Étudiant ou livre non trouvé.";
        }
        try {
            if (!book.isAvailable()) {
                System.out.println("Le livre n'est pas disponible.");
                return "Le livre n'est pas disponible.";
            }

            // Ajout de l'emprunt
            borrowDAO.addBorrow(borrow);
            System.out.println("Livre emprunté avec succès: " + borrow.getBook().getTitle() + " par " + borrow.getStudent().getName());
            return "Livre emprunté avec succès!";
        } catch (Exception e) {
            System.err.println("Une erreur s'est produite lors de l'emprunt du livre: " + e.getMessage());
            e.printStackTrace();
            return "Une erreur s'est produite lors de l'emprunt du livre.";
        }
    }

    public String returnBook(int studentId, int bookId) {
        borrowDAO.returnBook(studentId, bookId);

        bookDAO.updateBookAvailability(bookId, true);
        System.out.println("Livre retourné avec succès, ID étudiant: " + studentId + ", ID livre: " + bookId);
        return "Livre retourné avec succès!";
    }
}