package com.example.StudioMedico.dto;

import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class UserDTO {

    private String name;
    private String surname;
    private String email;

    private String createdBy;

    private String modifiedBy;

    private LocalDate createdOn;

    private LocalDate modifyOn;

    private RecordStatusENUM status;

    public UserDTO() {
    }

    public UserDTO(String name, String surname, String email, String createdBy, String modifiedBy, LocalDate createdOn, LocalDate modifyOn, RecordStatusENUM status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdOn = createdOn;
        this.modifyOn = modifyOn;
        this.status = status;
    }

    public RecordStatusENUM getStatus() {
        return status;
    }

    public void setStatus(RecordStatusENUM status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(LocalDate modifyOn) {
        this.modifyOn = modifyOn;
    }
}