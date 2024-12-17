package com.library.service;

import com.library.dao.StudentDAO;
import com.library.model.Student;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentService {

    private static final Logger logger = Logger.getLogger(StudentService.class.getName());
    private final StudentDAO studentDAO;
    private List<Student> students;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        this.students = studentDAO.getAllStudents();
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
        logger.info("Student added: " + student.getName() + ", Email: " + student.getEmail());
    }

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public Student findStudentByName(String name) {
        return studentDAO.findStudentByName(name);
    }

    public void getAllStudents() {
        if (students.isEmpty()) {
            logger.warning("No students found.");
        } else {
            for (Student student : students) {
                logger.info("Student - Name: " + student.getName() + ", Email: " + student.getEmail());
            }
        }
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
        logger.info("Student updated: " + student.getName() + ", Email: " + student.getEmail());
    }

    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
        logger.info("Student deleted with ID: " + studentId);
    }
}
