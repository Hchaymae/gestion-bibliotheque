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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) throws ParseException {
        final Logger logger = LoggerFactory.getLogger(Main.class);

        Scanner scanner = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        StudentDAO studentDAO = new StudentDAO();
        BookService bookService = new BookService(bookDAO);
        StudentService studentService = new StudentService(studentDAO);
        BorrowDAO borrowDAO = new BorrowDAO(studentDAO, bookDAO);
        BorrowService borrowService = new BorrowService(borrowDAO, bookDAO, studentDAO);

        boolean running = true;

        while (running) {
            logger.info("\n===== Menu =====");
            logger.info("1. Ajouter un livre");
            logger.info("2. Afficher les livres");
            logger.info("3. Ajouter un étudiant");
            logger.info("4. Afficher les étudiants");
            logger.info("5. Emprunter un livre");
            logger.info("6. Afficher les emprunts");
            logger.info("7. Quitter");

            logger.info("Choisir une option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Ajouter un livre
                    logger.info("Entrez le titre du livre: ");
                    String title = scanner.nextLine();
                    logger.info("Entrez l'auteur du livre: ");
                    String author = scanner.nextLine();
                    logger.info("Entrez l'année de publication: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    Book newBook = new Book(title, author, year);
                    bookService.addBook(newBook);
                    System.out.println("Livre ajouté avec succès!");
                    break;

                case 2:
                    // Afficher les livres
                    System.out.println("\nListe des livres disponibles:");
                    bookService.getAllBooks().forEach(System.out::println);
                    break;

                case 3:
                    // Ajouter un étudiant
                    logger.info("Entrez le nom de l'étudiant: ");
                    String studentName = scanner.nextLine();
                    Student newStudent = new Student(studentName);
                    studentService.addStudent(newStudent);
                    System.out.println("Étudiant ajouté avec succès!");
                    break;

                case 4:
                    // Afficher les étudiants
                    System.out.println("\nListe des étudiants:");
                    studentService.getAllStudents().forEach(System.out::println);
                    break;

                case 5:
                    // Emprunter un livre
                    logger.info("Entrez le nom de l'étudiant: ");
                    String studentNameForBorrow = scanner.nextLine();
                    logger.info("Entrez le titre du livre: ");
                    String bookTitleForBorrow = scanner.nextLine();

                    logger.info("Entrez la date de retour (jj/mm/aaaa): ");

//                     System.out.print("Entrez la date de retour (jj/MM/aaaa): ");

                    String returnDateStr = scanner.nextLine();

                    Student studentForBorrow = studentService.findStudentByName(studentNameForBorrow);
                    Book bookForBorrow = bookService.findBookByTitle(bookTitleForBorrow);

                    if (studentForBorrow != null && bookForBorrow != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date returnDate = sdf.parse(returnDateStr);

                        Borrow borrow = new Borrow(studentForBorrow, bookForBorrow, new Date(), returnDate);
                        borrowService.addBorrow(borrow);

                        System.out.println("Livre emprunté avec succès!");

                    } else {
                        logger.warn("Étudiant ou livre introuvable.");
                    }
                    break;

                case 6:
                    // Afficher les emprunts
                    System.out.println("\nListe des emprunts:");
                    borrowService.getAllBorrows().forEach(System.out::println);
                    break;

                case 7:
                    running = false;
                    logger.info("Au revoir!");
                    break;

                default:

                    logger.warn("Option invalide.");

//                     System.out.println("Option invalide. Veuillez choisir une option valide.");

            }
        }
        scanner.close();
    }
}
