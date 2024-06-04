package com.studentservice.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "registration_no" , nullable = false, unique = true)
   private String regNo;

   @Column(nullable = false)
   private String firstName;
   private String middleName;

   @Column(nullable = false)
   private String lastName;
   private LocalDate dob;
   private String gender;
   private String streetAddress;

   private String cityName;

   private String stateName;
   
   private Long zipCode;

   @Column(nullable = false)
   private String email;

   @Column(nullable = false)
   private Long phone;

   @Column(nullable = true)
   private String imagePath;

}
