package com.library;

import com.library.dao.BookDAO;
import com.library.dao.StudentDAO;
import com.library.service.BorrowService;
import com.library.service.BookService;
import com.library.service.StudentService;
import com.library.model.Book;
import com.library.model.Student;
import com.library.model.Borrow;
import com.library.dao.BorrowDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        StudentDAO studentDAO = new StudentDAO();
        BookService bookService = new BookService(bookDAO);
        StudentService studentService = new StudentService(studentDAO);
        BorrowDAO borrowDAO = new BorrowDAO(studentDAO, bookDAO);
        BorrowService borrowService = new BorrowService(borrowDAO, bookDAO, studentDAO);

        boolean running = true;

        while (running) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Ajouter un étudiant");
            System.out.println("4. Afficher les étudiants");
            System.out.println("5. Emprunter un livre");
            System.out.println("6. Afficher les emprunts");
            System.out.println("7. Quitter");

            System.out.println("Choisir une option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Ajouter un livre
                    System.out.println("Entrez le titre du livre: ");
                    String title = scanner.nextLine();
                    System.out.println("Entrez l'auteur du livre: ");
                    String author = scanner.nextLine();
                    System.out.println("Entrez l'année de publication: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    Book newBook = new Book(title, author, year);
                    bookService.addBook(newBook);
                    break;

                case 2:
                    // Afficher les livres
                    bookService.getAllBooks();
                    break;

                case 3:
                    // Ajouter un étudiant
                    System.out.println("Entrez le nom de l'étudiant: ");
                    String studentName = scanner.nextLine();
                    Student newStudent = new Student(studentName);
                    studentService.addStudent(newStudent);
                    break;

                case 4:
                    // Afficher les étudiants
                    studentService.getAllStudents();
                    break;

                case 5:
                    // Emprunter un livre
                    System.out.println("Entrez le nom de l'étudiant: ");
                    String studentNameForBorrow = scanner.nextLine();
                    System.out.println("Entrez le titre du livre: ");
                    String bookTitleForBorrow = scanner.nextLine();

                    System.out.println("Entrez la date de retour (jj/mm/aaaa): ");
                    String returnDateStr = scanner.nextLine();

                    Student studentForBorrow = studentService.findStudentByName(studentNameForBorrow);
                    Book bookForBorrow = bookService.findBookByTitle(bookTitleForBorrow);

                    Date returnDate = null;

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    returnDate = sdf.parse(returnDateStr);

                    if (studentForBorrow != null && bookForBorrow != null) {
                        Borrow borrow = new Borrow(studentForBorrow, bookForBorrow, new Date(), returnDate);
                        borrowService.addBorrow(borrow);
                    } else {
                        System.out.println("Étudiant ou livre introuvable.");
                    }
                    break;

                case 6:
                    // Afficher les emprunts
                    borrowService.getAllBorrows();
                    break;

                case 7:
                    running = false;
                    System.out.println("Au revoir!");
                    break;

                default:
                    System.out.println("Option invalide.");
            }
        }

        scanner.close();
    }
}