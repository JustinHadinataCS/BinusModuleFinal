package com.example.demo.Subject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SubjectService {

    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> getAllSubjects(String keyword) {
        if (keyword != null) {
            List<Subject> result = new ArrayList<>();
            for (Subject subject : subjects) {
                if (subject.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        subject.getCode().toLowerCase().contains(keyword.toLowerCase())) {
                    result.add(subject);
                }
            }
            return result;
        }
        return subjects;
    }


    public Subject getSubjectByName(String name) {
        return linearSearchByName(name);
    }

    public Subject getSubjectByNameBinary(String name) {
        return binarySearchByName(name);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
        Collections.sort(subjects, Comparator.comparing(Subject::getName));
    }

    public void updateSubject(String code, Subject subject) {
        for (int i = 0; i < subjects.size(); i++) {
            Subject s = subjects.get(i);
            if (s.getCode().equals(code)) {
                subjects.set(i, subject);
                Collections.sort(subjects, Comparator.comparing(Subject::getName));
                return;
            }
        }
    }

    public void deleteSubject(String code) {
        subjects.removeIf(s -> s.getCode().equals(code));
        Collections.sort(subjects, Comparator.comparing(Subject::getName));
    }

    private Subject linearSearchByName(String name) {
        for (Subject subject : subjects) {
            if (subject.getName().equalsIgnoreCase(name)) {
                return subject;
            }
        }
        return null;
    }

    private Subject binarySearchByName(String name) {
        int left = 0;
        int right = subjects.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Subject midSubject = subjects.get(mid);
            int cmp = midSubject.getName().compareTo(name);

            if (cmp == 0) {
                return midSubject;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public void performanceTest() {
        // Populate data for testing
        for (int i = 0; i < 1000; i++) {
            String code = "CSE" + i;
            String name = "Subject " + i;
            Subject subject = new Subject((long) i, code, name);
            addSubject(subject);
        }

        // Performance test for Linear Search by Name in ArrayList
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linearSearchByName("Subject " + (i + 500)); // random access pattern
        }
        long endTime = System.nanoTime();
        System.out.println("Linear Search by Name in ArrayList time: " + (endTime - startTime) + " ns");

        // Performance test for Binary Search by Name in ArrayList
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            binarySearchByName("Subject " + (i + 500)); // random access pattern
        }
        endTime = System.nanoTime();
        System.out.println("Binary Search by Name in ArrayList time: " + (endTime - startTime) + " ns");
    }
}
