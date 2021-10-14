package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//SERVICE LAYER (business logic to manage students).
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired //StudentRepository is autowired for Rest. studentRepository is auto-instantiated/injected into the constructor.
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        //this.studentRepository = new studentRepository(); //to be avoided, insteand use dependency injection.
    } 

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.
            findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                "student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    //Transactional annotation allows the entity (Student table/object) to go into manage state.
    //It's to avoid query the DB in JPSQL and instead use directly class methods such as getters/setters.
    @Transactional
    public void updateStudent(Long studentId, String name, String email ) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalStateException(
                "student with id " + studentId + " does not exists"));
            
        if (name != null && 
                name.length() > 0 && 
                !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && 
                email.length() > 0 && 
                !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken.");
            }
           student.setEmail(email);
        }
    }

}
