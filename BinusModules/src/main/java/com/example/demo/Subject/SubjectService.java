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
    private void mergeSort(List<Subject> subjects) {
        if (subjects.size() > 1) {
            int mid = subjects.size() / 2;
            List<Subject> left = subjects.subList(0, mid);
            List<Subject> right = subjects.subList(mid, subjects.size());

            mergeSort(left);
            mergeSort(right);

            merge(left, right, subjects);
        }
    }

    private void merge(List<Subject> left, List<Subject> right, List<Subject> subjects) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareTo(right.get(j).getName()) <= 0) {
                subjects.set(k++, left.get(i++));
            } else {
                subjects.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            subjects.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            subjects.set(k++, right.get(j++));
        }
    }

    public void mergeSortByName() {
        mergeSort(subjects);
    }
    private void quickSort(List<Subject> subjects, int low, int high) {
        if (low < high) {
            int pi = partition(subjects, low, high);

            quickSort(subjects, low, pi - 1);
            quickSort(subjects, pi + 1, high);
        }
    }

    private int partition(List<Subject> subjects, int low, int high) {
        String pivot = subjects.get(high).getName();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (subjects.get(j).getName().compareTo(pivot) < 0) {
                i++;
                Collections.swap(subjects, i, j);
            }
        }

        Collections.swap(subjects, i + 1, high);
        return i + 1;
    }

    public void quickSortByName() {
        quickSort(subjects, 0, subjects.size() - 1);
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

        // Performance test for Merge Sort
        startTime = System.nanoTime();
        mergeSortByName();
        endTime = System.nanoTime();
        System.out.println("Merge Sort by Name in ArrayList time: " + (endTime - startTime) + " ns");

        // Performance test for Quick Sort
        startTime = System.nanoTime();
        quickSortByName();
        endTime = System.nanoTime();
        System.out.println("Quick Sort by Name in ArrayList time: " + (endTime - startTime) + " ns");
    }


}
