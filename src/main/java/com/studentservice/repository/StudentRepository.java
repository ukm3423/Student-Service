package com.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentservice.models.Student;



public interface StudentRepository extends JpaRepository<Student , Long>{
    
    public Student findByRegNo(String regNo);

    public Student findByEmail(String email);

}
