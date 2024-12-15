package com.library.service;

import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;
    private List<Student> students;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        this.students = studentDAO.getAllStudents();
    }

    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public Student findStudentByName(String name) {
        return studentDAO.findStudentByName(name);
    }

    public void getAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Aucun livre disponible.");
        } else {
            for (Student student : students) {
                System.out.println("Nom: " + student.getName() + ", Email: " + student.getEmail());
            }
        }
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
    }
}
