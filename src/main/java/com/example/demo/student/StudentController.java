package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//API LAYER
@RestController //make this class to serve as Rest endpoints
@RequestMapping(path = "api/v1/student")
public class StudentController {

     //Spring Bean.
    private final StudentService studentService;

    //StudentService is autowired for Rest. 
    //studentService is auto-instantiated/injected into the constructor.
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;

        //Below statement to be avoided, insteand use dependency injection.
        //this.studentService = new studentService();
    }

    //Rest endpoint: Get data from the server.
    @GetMapping
	public List<Student> getStudents() {
        return studentService.getStudents();
	}

    //Rest endpoint: Post data to the server.
    //Take response body and map it into a student object.
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {    
        studentService.addNewStudent(student);
    }

    //Rest endpoint: Delete data from the server.
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
    
    //Rest endpoint: Update data from the server.
    @PutMapping(path = "{studentId}") 
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        studentService.updateStudent(studentId, name, email);
    }

}
