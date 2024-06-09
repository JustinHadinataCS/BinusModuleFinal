package com.example.demo.Subject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerformanceTestService {
    public void testPerformance() {
        List<Subject> arrayList = new ArrayList<>();
        Map<String, Subject> hashMap = new HashMap<>();

        // Add subjects for testing
        for (int i = 0; i < 1000; i++) {
            Subject subject = new Subject((long) i, "CODE" + i, "Subject" + i);
            arrayList.add(subject);
            hashMap.put(subject.getCode(), subject);
        }

        // Test search performance
        long startTime = System.nanoTime();
        searchInList(arrayList, "CODE500");
        long endTime = System.nanoTime();
        System.out.println("ArrayList search time: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        searchInMap(hashMap, "CODE500");
        endTime = System.nanoTime();
        System.out.println("HashMap search time: " + (endTime - startTime) + " ns");
    }

    private Subject searchInList(List<Subject> list, String code) {
        for (Subject subject : list) {
            if (subject.getCode().equals(code)) {
                return subject;
            }
        }
        return null;
    }

    private Subject searchInMap(Map<String, Subject> map, String code) {
        return map.get(code);
    }
}
