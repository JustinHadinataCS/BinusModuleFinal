package com.example.demo.Student;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> GetStudent (){
        return studentService.GetStudent();
    }

    @PostMapping
    public void RegisterNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{StudentId}")
    public void DeleteStudent (@PathVariable("StudentId") Long StudentId){
        studentService.DeleteStudent(StudentId);
    }

    @PutMapping(path = "{StudentID}")
    public void UpdateStudent(@PathVariable("StudentId") Long StudentId,
                              @RequestBody Student UpdateStudent){
        studentService.UpdateStudent(StudentId, UpdateStudent);
    }
}
