package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.AppUser.AppUser;
import com.example.demo.Registration.RegistrationRequest;
import com.example.demo.Registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Service
public class subjectService {

    //Delete this 
    @Autowired
    private SubjectRepository subjectRepository;

    private List<Subject> subjects = new ArrayList<>();


    public List<Subject> getAllSubjects() {
//        return subjects;
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
