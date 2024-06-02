package com.example.demo.Student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student michael = new Student(
                    2L, "Michael","2802319230"
            );
            repository.saveAll(
                    List.of(michael)
            );

        };
    }
}
