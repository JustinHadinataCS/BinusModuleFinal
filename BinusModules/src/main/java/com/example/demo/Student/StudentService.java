package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    public void DeleteStudent(Long StudentId){
        boolean exists = studentRepository.existsById(StudentId);
        if(!exists){
            throw new IllegalStateException("Student with id " + StudentId + "does not exist!");
        }
        studentRepository.deleteById(StudentId);
    }

    @Transactional
    public void UpdateStudent(Long StudentId, Student UpdateStudent){
        Student student = studentRepository.findById(StudentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + StudentId + "Does not exist")

                );
        if (UpdateStudent.getName() != null && !Objects.equals(student.getName(), UpdateStudent.getName())){
            student.setName(UpdateStudent.getName());
        }
        if(UpdateStudent.getEmail() != null && !Objects.equals(student.getEmail(), UpdateStudent.getEmail())){
            Optional<Student>  StudentOptional = studentRepository.findStudentByEmail(UpdateStudent.getEmail());
            if(StudentOptional.isPresent()){
                throw new IllegalStateException("Email is not available");
            }
        }

    }


}

