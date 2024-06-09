package com.example.demo.Subject;

import com.example.demo.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    @Query("SELECT subject FROM Subject subject WHERE LOWER(CONCAT(subject.name, ' ', subject.code)) LIKE %?1%")
    public List<Subject> searchCourse(String keyword);
}
