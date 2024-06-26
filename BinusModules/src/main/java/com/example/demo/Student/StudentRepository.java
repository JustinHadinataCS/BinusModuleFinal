package com.example.demo.Student;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("Select s from Student s where s.student_id = ?1")
    Optional<Student> findStudentById(String student_id);
}
