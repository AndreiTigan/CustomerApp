package com.example.POC.model.dto;

import com.example.POC.model.entity.Customer;

import java.time.LocalDate;

public class CustomerDto {
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

    public Customer convertToEntity() {
        Customer customer = new Customer();
        customer.setId(this.getId());
        customer.setFirstName(this.getFirstName());
        customer.setLastName(this.getLastName());
        customer.setEmail(this.getEmail());
        customer.setDob(this.getDob());
        customer.setCity(this.getCity());
        return customer;
    }
}
