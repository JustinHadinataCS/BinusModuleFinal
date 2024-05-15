package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class subjectService {
    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> getAllSubjects() {
        return subjects;
    }

    public Subject getSubjectByCode(String code) {
        return subjects.stream().filter(s -> s.getCode().equals(code)).findFirst().orElse(null);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void updateSubject(String code, Subject subject) {
        for (int i = 0; i < subjects.size(); i++) {
            Subject s = subjects.get(i);
            if (s.getCode().equals(code)) {
                subjects.set(i, subject);
                return;
            }
        }
    }

    public void deleteSubject(String code) {
        subjects.removeIf(s -> s.getCode().equals(code));
    }
}
