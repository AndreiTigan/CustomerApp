package com.example.POC.model.entity;


import com.example.POC.model.dto.CustomerDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class Customer {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CustomerDto convertToDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(this.getId());
        customerDto.setFirstName(this.getFirstName());
        customerDto.setLastName(this.getLastName());
        customerDto.setEmail(this.getEmail());
        customerDto.setDob(this.getDob());
        customerDto.setCity(this.getCity());
        return customerDto;
    }
}
