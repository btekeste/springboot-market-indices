package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//DATA ACCESS LAYER
//Data Access Object class
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    //JPQL language: SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1") 
    Optional<Student> findStudentByEmail(String email);

}
