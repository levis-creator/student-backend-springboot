package com.micosoft.tutorial.Student;

import com.micosoft.tutorial.Student.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private  final StudentService studentService;
@Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
    @PostMapping
    public void addStudent(@RequestBody Student student){
    studentService.addNewStudent(student);
    }
    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
    studentService.deleteStudent(studentId);
    }
  @PutMapping("{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,@RequestParam(required = false) String name, @RequestParam(required = false) String email){
    studentService.updateStudent(studentId, name, email);

  }
}
