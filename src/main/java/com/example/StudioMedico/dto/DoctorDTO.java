package com.example.StudioMedico.dto;

import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;

import java.time.LocalDate;

public class DoctorDTO extends UserDTO{
    private Long idDoctor;
    private String workplace;
    private String specialization;


    public DoctorDTO(String name, String surname, String email, String createdBy, String modifiedBy, LocalDate createdOn, LocalDate modifyOn, RecordStatusENUM status, String workplace, String specialization) {
        super(name, surname, email, createdBy, modifiedBy, createdOn, modifyOn, status);
        this.workplace=workplace;
        this.specialization=specialization;

    }

    public DoctorDTO() {
        super();
    }

    public Long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
