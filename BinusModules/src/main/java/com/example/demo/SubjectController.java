package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private subjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{code}")
    public Subject getSubjectByCode(@PathVariable String code) {
        return subjectService.getSubjectByCode(code);
    }

    @PostMapping
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
