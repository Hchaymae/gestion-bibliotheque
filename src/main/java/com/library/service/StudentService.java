package com.library.service;

import com.library.dao.StudentDAO;
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
        System.out.println("Student added: " + student.getName() + ", Email: " + student.getEmail());
    }

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public Student findStudentByName(String name) {
        return studentDAO.findStudentByName(name);
    }

    public void getAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println("Student - Name: " + student.getName() + ", Email: " + student.getEmail());
            }
        }
    }

    public void updateStudent(Student student) {
        studentDAO.updateStudent(student);
        System.out.println("Student updated: " + student.getName() + ", Email: " + student.getEmail());
    }

    public void deleteStudent(int studentId) {
        studentDAO.deleteStudent(studentId);
        System.out.println("Student deleted with ID: " + studentId);
    }
}