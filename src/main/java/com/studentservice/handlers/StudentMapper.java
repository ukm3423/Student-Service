package com.studentservice.handlers;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;

import com.studentservice.dto.StudentResponse;
import com.studentservice.models.Student;

import org.springframework.data.domain.Page;


import java.util.List;

public class StudentMapper {
    public static StudentResponse toStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .fullname(student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName())
                .email(student.getEmail())
                .phoneNumber(student.getPhone())
                .dob(student.getDob())
                .build();
    }

    public static Page<StudentResponse> toStudentResponsePage(Page<Student> studentPage) {
        List<StudentResponse> studentResponses = studentPage.stream()
                .map(StudentMapper::toStudentResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(studentResponses, studentPage.getPageable(), studentPage.getTotalElements());
    }
}
