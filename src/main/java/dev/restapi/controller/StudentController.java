package dev.restapi.controller;

import dev.restapi.entity.Student;
import dev.restapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Student student) {

        Map<String, Object> responseObject = new HashMap<>();
        try {
            System.out.println("Add student: START");
            repo.save(student);
            int responseStatus = HttpStatus.CREATED.value();
            System.out.println("Response Status: " + responseStatus );

            responseObject.put("message", "Successfully added student");
            responseObject.put("status", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);

        } catch (Exception e) {
            responseObject.put("message", "Failed to add student");
            responseObject.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
        }
    }


    @PutMapping("/students/update")
    public Student updateStudent(@RequestBody Student body) {
        System.out.println("Update Student: START");
        JSONObject requestBody = new JSONObject(body);
        System.out.println("Request Body: " + requestBody);

        int id = requestBody.optInt("idNumber");
        String studentName = requestBody.optString("name");
        String department = requestBody.optString("department");
        int score = requestBody.optInt("score");

        Student student = repo.findById(id).get();
        student.setName(studentName);
        student.setDepartment(department);
        student.setScore(score);
        repo.save(student);

        return student;

    }


    @DeleteMapping("/students/delete/{id}")
    public void deleteStudent(@PathVariable int id) {
        Student student = repo.findById(id).get();

        repo.delete(student);
    }


}
