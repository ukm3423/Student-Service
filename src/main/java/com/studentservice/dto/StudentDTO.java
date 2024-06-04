package com.studentservice.dto;

import java.time.LocalDate;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    
    private String regNo;

    private String firstName;

    private String middleName;

    private String lastName;

    private LocalDate dob;

    private String gender;

    private String streetAddress;

    private Long cityId;

    private Long stateId;

    private String zipcode;

    private String email;

    private String phone;

    @Nullable
    private MultipartFile image;

}
