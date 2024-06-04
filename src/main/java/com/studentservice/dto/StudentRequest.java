package com.studentservice.dto;

import java.time.LocalDate;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {

    @NotBlank(message = "Registration number is required")
    @Pattern(regexp = "REG\\d{3}", message = "Registration number must be in the format 'REG' followed by three digits")
    private String regNo;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot be longer than 50 characters")
    private String firstName;

    @Size(max = 50, message = "Middle name cannot be longer than 50 characters")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot be longer than 50 characters")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "male|female|other", message = "Gender must be 'male', 'female', or 'other'")
    private String gender;

    @NotBlank(message = "Street address is required")
    @Size(max = 100, message = "Street address cannot be longer than 100 characters")
    private String streetAddress;

    @NotBlank(message = "City is required")
    private Long cityId;

    @NotBlank(message = "State is required")
    private Long stateId;

    @NotBlank(message = "Zipcode is required")
    @Pattern(regexp = "\\d{6}", message = "Zipcode must be a 6-digit number")
    private String zipcode;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private String phone;

    // private byte[] image;
    @Nullable
    private MultipartFile image;

}
