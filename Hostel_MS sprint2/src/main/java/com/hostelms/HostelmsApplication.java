/*
 // Using SPRING BOOT,JPA REPOSITORY,
    LOMBOK,VALIDATION AND GLOBAL EXCEPTION IN HOSTEL MANAGEMENT SYSTEM 
 // ALLOTING ROOM TO USER
 //THERE ARE TWOUSERS
 //->ADMIN
 //->END USER
 // AND PRINT DATA OF ONE OR ALL USER USING LOGGER, DELETE USER AND ROOM USING DATA ACCESS OBJECT  
// CREATING AND USING GLOBAL EXCEPTION
 // ILLUSTRATING OBJECT RELATION MAPPING IN ENTITY 
 // ONE TOMANY RELATIONSHIP REPRESENT ONE ROOM CAN HAVE MANY USER
  #WRITTEN BY SANDEEP KUMAR
 */
package com.hostelms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HostelmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostelmsApplication.class, args);
	}

}
