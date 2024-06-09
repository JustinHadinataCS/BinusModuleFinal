package com.example.demo.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private PerformanceTestService performanceTestService;

    @GetMapping("/course")
    public String student(Model model, @Param("keyword") String keyword){
        List<Subject> subjectsList = subjectService.getAllSubjects(keyword);
        model.addAttribute("courseList", subjectsList);
        model.addAttribute("keyword", keyword);
        return "course";
    }

//    @GetMapping("/{code}")
//    public Subject getSubjectByCode(@PathVariable String code) {
//        return subjectService.getSubjectByCode(code);
//    }

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

    @GetMapping("/performance-test")
    public String performPerformanceTest() {
        performanceTestService.runPerformanceTest(); // Corrected method name
        return "Performance test completed. Check the logs for results.";
    }

}
