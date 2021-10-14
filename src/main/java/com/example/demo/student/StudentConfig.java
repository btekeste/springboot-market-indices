package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Annotation to make this class a configuration class to contain Beans.
@Configuration
public class StudentConfig {
    
     //Annotation to make this method as a bean so that it runs.
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                "Mariam",
                "mariam.jamal@gmail.com",
                LocalDate.of(2000, 1, 5)
            );
            Student alex = new Student(
                "Alex",
                "alex@gmail.com",
                LocalDate.of(2004, 1, 5)
            );

             //Hibernate is invoked to insert into DB.
            repository.saveAll(
                List.of(mariam, alex)
            );
        };
    }

}
