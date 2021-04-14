package com.ghailene.exceptionhandler.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ghailene.exceptionhandler.validator.MobileValidate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@MobileValidate(message = "Mobile number invalid")
public class Customer {
    @NotBlank
    @Size(max = 10)
    private String cin;
    private String firstname;
    private String lastName;
    @Valid
    private ContactInformation contact;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Europe/Paris")
    private Date birthdate;

    public Customer(String cin, String firstname, String lastName, ContactInformation contact, Date birthdate) {
        this.cin = cin;
        this.firstname = firstname;
        this.lastName = lastName;
        this.contact = contact;
        this.birthdate = birthdate;
    }


    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        cin = cin;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ContactInformation getContact() {
        return contact;
    }

    public void setContact(ContactInformation contact) {
        this.contact = contact;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
