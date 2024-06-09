package com.example.demo.Subject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class subjectService {

    //Delete this
    @Autowired
    private SubjectRepository subjectRepository;

    private List<Subject> subjects = new ArrayList<>();


    // Search system (if there is a keyword input)
    public List<Subject> getAllSubjects(String keyword) {
//        return subjects;

        if(keyword != null){
            return subjectRepository.searchCourse(keyword);
        }
        return subjectRepository.findAll();

    }

    public Subject getSubjectByCode(String code) {
//        return subjects.stream().filter(s -> s.getCode().equals(code)).findFirst().orElse(null);
        return subjectRepository.findById(code).orElse(null);

    }

    public void addSubject(Subject subject) {
//        subjects.add(subject);
        subjectRepository.save(subject);

    }

    public void updateSubject(String code, Subject subject) {
//        for (int i = 0; i < subjects.size(); i++) {
//            Subject s = subjects.get(i);
//            if (s.getCode().equals(code)) {
//                subjects.set(i, subject);
//                return;
//            }
//        }
        subjectRepository.save(subject);

    }

    public void deleteSubject(String code) {

//        subjects.removeIf(s -> s.getCode().equals(code));
        subjectRepository.deleteById(code);

    }

}
