package dev.restapi.controller;

import dev.restapi.entity.Student;
import dev.restapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository repo;

    //get students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        List<Student> students = repo.findAll();
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();

        return student;
    }

    @PostMapping("/students/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {

        System.out.println("Add student start");
        repo.save(student);

        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.CREATED)
                .body("");
        System.out.println(response.getStatusCode());

    }

}
