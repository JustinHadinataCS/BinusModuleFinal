package com.example.demo.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PerformanceTestService {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/performance-test")
    public String runPerformanceTest() {
        subjectService.performanceTest();
        return "Performance test executed. Check the logs for results.";
    }
}
