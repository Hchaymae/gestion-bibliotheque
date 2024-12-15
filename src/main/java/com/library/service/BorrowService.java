package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;

import java.util.List;

public class BorrowService {

    private final BorrowDAO borrowDAO;
    private  BookDAO bookDAO;
    private StudentDAO studentDAO;

    public BorrowService(BorrowDAO borrowDAO, BookDAO bookDAO, StudentDAO studentDAO) {
        this.borrowDAO = borrowDAO;
        this.bookDAO=bookDAO;
        this.studentDAO=studentDAO;
    }

    public List<Borrow> getAllBorrows() {
        return borrowDAO.getAllBorrows();
    }

    public String addBorrow(Borrow borrow) {
        Book book = bookDAO.getBookById(borrow.getBook().getId());

        if (borrow.getStudent() == null || borrow.getBook() == null) {
            return "Étudiant ou livre non trouvé.";
        }
        try {
            if (!book.isAvailable()) {
                return "Le livre n'est pas disponible.";
            }

            // Ajout de l'emprunt
            borrowDAO.addBorrow(borrow);
            return "Livre emprunté avec succès!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Une erreur s'est produite lors de l'emprunt du livre.";  // Ajout d'un retour de message en cas d'exception
        }
    }



    public String returnBook(int studentId, int bookId) {
        borrowDAO.returnBook(studentId, bookId);

        bookDAO.updateBookAvailability(bookId, true);

        return "Livre retourné avec succès!";
    }

}
