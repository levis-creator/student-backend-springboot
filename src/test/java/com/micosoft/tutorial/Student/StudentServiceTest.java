package com.micosoft.tutorial.Student;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock private  StudentRepository studentRepository;
    StudentService underTest;

    @BeforeEach
    void setUp(){
        underTest=new StudentService(studentRepository);
    }

    @Test
    void thisShouldGetStudentsFromDb() {
//        when
        underTest.getStudents();
//        then
        verify(studentRepository).findAll();

    }

    @Test
    void thisWillAddNewStudent() {
//        given
        String email="levis.nyingi@gmail.com";
        Student student= new Student(
                "Levi", LocalDate.of(2000, Month.JULY,27), email
        );
//        when
       underTest.addNewStudent(student);
//        then
//        this is getting in data from the inserted from class
        ArgumentCaptor<Student> studentArgumentCaptor= ArgumentCaptor.forClass(Student.class);
//        this is getting data ran by repository
        verify(studentRepository).save(studentArgumentCaptor.capture());
//         this is storing data that was captured when entering the class
        Student captorValue = studentArgumentCaptor.getValue();
//        this is testing if the data entered from beginning is the one exiting
        assertThat(captorValue).isEqualTo(student);

    }
    @Test
    @Disabled
    void whenEmailIsTaken() {
//        given

        String email="levis.nyingi@gmail.com";
        Student student= new Student(
                "Levi", LocalDate.of(2000, Month.JULY,27), email
        );

        given(studentRepository.findStudentByEmail(student.getEmail()).isPresent()).willReturn(true);
//        when
//        then
        assertThatThrownBy(()->underTest.addNewStudent(student)).isInstanceOf(IllegalStateException.class).hasMessageContaining(   "Email taken");

   }

    @Test
    @Disabled
    void deleteStudent() {
    }
}