package com.micosoft.tutorial.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student levi =new Student(
                  "Levi", LocalDate.of(2000, Month.JULY,27),"levis.nyingi@gmail.com"
            );
            Student alex =new Student(
                     "Levi", LocalDate.of(2000, Month.JULY,27),"levis.nyingi2@gmail.com"
            );
            repository.saveAll(List.of(levi, alex));

        };
    }
}
