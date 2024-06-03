package com.example.demo.Subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubjectController {

    @Autowired
    private subjectService subjectService;

    @GetMapping("/course")
    public String student(Model model){
        List<Subject> subjectsList = subjectService.getAllSubjects();
        model.addAttribute("courseList", subjectsList);
        return "course";
    }

    @GetMapping("/{code}")
    public Subject getSubjectByCode(@PathVariable String code) {
        return subjectService.getSubjectByCode(code);
    }

    @PostMapping("api/v1/course")
    public void addSubject(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
    }

    @PutMapping("/{code}")
    public void updateSubject(@PathVariable String code, @RequestBody Subject subject) {
        subjectService.updateSubject(code, subject);
    }

    @DeleteMapping("/{code}")
    public void deleteSubject(@PathVariable String code) {
        subjectService.deleteSubject(code);
    }
}
