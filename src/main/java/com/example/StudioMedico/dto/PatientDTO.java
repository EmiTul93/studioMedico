package com.example.StudioMedico.dto;

import com.example.StudioMedico.entities.recordEnum.GenderENUM;
import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;

import java.time.LocalDate;

public class PatientDTO extends UserDTO{
    private Long idPatient;
    private String phoneNumber;
    private String fiscalCode;
    private GenderENUM gender;

    public PatientDTO(String name, String surname, String email, String createdBy, String modifiedBy, LocalDate createdOn, LocalDate modifyOn, RecordStatusENUM status, String phoneNumber, String fiscalCode, GenderENUM gender) {
        super(name, surname, email, createdBy, modifiedBy, createdOn, modifyOn,status);
        this.phoneNumber = phoneNumber;
        this.fiscalCode = fiscalCode;
        this.gender = gender;
    }

    public PatientDTO() {
        super();
    }

    public Long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public GenderENUM getGender() {
        return gender;
    }

    public void setGender(GenderENUM gender) {
        this.gender = gender;
    }

}
