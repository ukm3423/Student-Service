package com.studentservice.services;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.tomcat.util.openssl.pem_password_cb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.studentservice.dto.StudentDTO;
import com.studentservice.dto.StudentResponse;
import com.studentservice.handlers.StudentMapper;
import com.studentservice.models.Student;
import com.studentservice.repository.StudentRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository stdRepo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private StateRepository stateRepo;

    @Autowired
    private FileStorageService fileStorageService;

    public StudentResponse saveStudent(StudentDTO req) throws IOException {
        String photoFileName = fileStorageService.storeFile(req.getImage());

        City city = cityRepo.findById(req.getCityId()).get();
        State state = stateRepo.findById(req.getStateId()).get();

        Student std = Student.builder()
                .regNo(req.getRegNo())
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .phone(Long.parseLong(req.getPhone()))
                .streetAddress(req.getStreetAddress())
                .cityName(city.getCity())
                .stateName(state.getState())
                .zipCode(Long.parseLong(req.getZipcode()))
                .gender(req.getGender())
                .imagePath(photoFileName)
                .dob(req.getDob())
                .build();

        stdRepo.save(std);

        StudentResponse resp = StudentResponse.builder()
                .id(std.getId())
                .fullname(std.getFirstName() + " " + std.getMiddleName() + " " + std.getLastName())
                .email(std.getEmail())
                .phoneNumber(std.getPhone())
                .dob(std.getDob())
                .build();
        return resp;

    }

    
    
    public Student searchByRegisterNo(String registerNo){
        Student student = stdRepo.findByRegNo(registerNo);
        return student;
    }


    public Student searchByEmailId(String email) {
        return stdRepo.findByEmail(email);

    }

    public Page<StudentResponse> getStudentList(Pageable pageable) {
        Page<Student> studentPage = stdRepo.findAll(pageable);
        return StudentMapper.toStudentResponsePage(studentPage);
    }
}
