package com.example.ws1301.ws1301.model;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    private String id;

    @NotNull
    @Size(min = 3, max = 64, message = "name should be valid")
    private String name;

    @NotNull
    @Email(message = "Enter Valid Email id")
    private String email;

    @NotNull
    @Size(min = 7, message = "Enter valid phone number")
    private String phone;

    @NotNull
    @Past(message = "DOB should be from past")
    private LocalDate dob;


    public Contact(){}
    public Contact(String id, String name, String email, String phone, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", dob=" + dob + "]";
    }

}
