package com.library;


import com.library.dao.StudentDAO;
import com.library.model.Student;
import com.library.service.StudentService;
import com.library.util.DbConnection;
import org.junit.AfterClass;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
class StudentServiceTest {
    private StudentService studentService;
    private StudentDAO studentDAO;

    @BeforeEach
    void setUp() {
        studentDAO = new StudentDAO();
        studentService = new StudentService(studentDAO);
    }


    @AfterAll
    public static void tearDown() {
        System.out.println("Test teardown: Clearing database...");
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Disable foreign key checks (if needed)
            statement.execute("SET FOREIGN_KEY_CHECKS = 0");

            // Truncate all tables
            statement.execute("TRUNCATE TABLE Borrow");
            statement.execute("TRUNCATE TABLE Book");
            statement.execute("TRUNCATE TABLE Student");

            // Re-enable foreign key checks
            statement.execute("SET FOREIGN_KEY_CHECKS = 1");

            System.out.println("Database cleared successfully!");
        } catch (SQLException e) {
            System.err.println("Error clearing database: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    void testAddStudent() {
        Student student = new Student(3, "Chaymae", "chaymae@example.com");
        studentService.addStudent(student);
        assertEquals(1, studentDAO.getAllStudents().size());
        assertEquals("Chaymae", studentDAO.getStudentById(1).getName());
    }

    @Test
    @Order(2)
    void testUpdateStudent() {
//        studentService.addStudent(1, "Alice", "alice@example.com");
        Student studentupdated = new Student(1, "Alice Smith", "alice.smith@example.com");
        studentService.updateStudent(studentupdated);
        assertEquals("Alice Smith", studentDAO.getStudentById(1).getName());
    }

    @Test
    @Order(3)
    void testDeleteStudent() {
        studentService.deleteStudent(1);
        assertNull(studentDAO.getStudentById(1));
    }

    @Test
    @Order(4)
    void testGetAllStudents() {
        Student student1 = new Student(2, "Alice", "ali@example.com");
        Student student2 = new Student(2, "Bob", "bob@example.com");
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        assertEquals(2, studentDAO.getAllStudents().size());
    }
}
