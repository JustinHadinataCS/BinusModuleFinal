package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Autowired
    public List<Student> GetStudent (){ return studentRepository.findAll(); }

    public void addNewStudent(Student student){
        Optional<Student> Student_Optional = studentRepository.findStudentByEmail(student.getEmail());
        if(Student_Optional.isPresent()){
            throw new IllegalStateException("Student ID is not available");
        }
        studentRepository.save(student);
    }

}

