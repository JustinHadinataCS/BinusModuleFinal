package com.example.demo.Subject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubjectService {

    private Map<String, Subject> subjects = new HashMap<>();

    // Search system (if there is a keyword input)
    public List<Subject> getAllSubjects(String keyword) {
        List<Subject> subjectList = new ArrayList<>(subjects.values());
        if (keyword != null && !keyword.isEmpty()) {
            List<Subject> filteredSubjects = new ArrayList<>();
            for (Subject subject : subjectList) {
                if (subject.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        subject.getCode().toLowerCase().contains(keyword.toLowerCase())) {
                    filteredSubjects.add(subject);
                }
            }
            return filteredSubjects;
        }
        return subjectList;
    }

    public Subject getSubjectByCode(String code) {
        return subjects.get(code);
    }

    public void addSubject(Subject subject) {
        subjects.put(subject.getCode(), subject);
    }

    public void updateSubject(String code, Subject updatedSubject) {
        subjects.put(code, updatedSubject);
    }

    public void deleteSubject(String code) {
        subjects.remove(code);
    }
}

