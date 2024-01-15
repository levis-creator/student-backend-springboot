package com.micosoft.tutorial.Student;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    StudentService underTest;
    String email = "levis.nyingi@gmail.com";
    Student student = new Student("Levi", LocalDate.of(2000, Month.JULY, 27), email);
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
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
//        when
        underTest.addNewStudent(student);
//        then
//        this is getting in data from the inserted from class
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
//        this is getting data ran by repository
        verify(studentRepository).save(studentArgumentCaptor.capture());
//         this is storing data that was captured when entering the class
        Student captorValue = studentArgumentCaptor.getValue();
//        this is testing if the data entered from beginning is the one exiting
        assertThat(captorValue).isEqualTo(student);

    }

    @Test
    void whenEmailIsTaken() {
//        given


        given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(student));
//        when
//        then
        assertThatThrownBy(() -> underTest.addNewStudent(student)).isInstanceOf(IllegalStateException.class).hasMessageContaining("Email taken");
        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudentWhenIdIsFound() {
//        given
        underTest.deleteStudent(student.getId());

    }
}