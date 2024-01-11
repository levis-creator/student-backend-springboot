package com.micosoft.tutorial.Student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private  StudentRepository underTest;
   @AfterEach
   void tearDown(){
       underTest.deleteAll();
   }

    @Test
    void checkingEmailExistence() {
//      given
        String email="levis.nyingi@gmail.com";
        Student student= new Student(
                "Levi", LocalDate.of(2000, Month.JULY,27), email
        );
        underTest.save(student);

//      when
        Optional<Student> exists=underTest.findStudentByEmail(email);
//      then
        assertThat(exists.isPresent()).isTrue();
    }
    @Test
    void checkingIfStudentEmailDoesnotExist() {
//      given
        String email="levis.nyingi3@gmail.com";

//      when
        Optional<Student> exists=underTest.findStudentByEmail(email);
//      then
        assertThat(exists.isPresent()).isFalse();
    }
}