package com.example.StudioMedico.entities;

import com.example.StudioMedico.entities.recordEnum.GenderENUM;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Long idPatient;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "fiscal_code", nullable = false)
    private String fiscalCode;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderENUM gender;


    @ManyToOne
    @JoinColumn(name = "id_doctor")
    @JsonIgnore
    private Doctor doctor;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Prenotation> prenotationList;


    public Patient(String name, String surname, String email, String phoneNumber, String fiscalCode, GenderENUM gender) {
        super(name, surname, email);
        this.phoneNumber = phoneNumber;
        this.fiscalCode = fiscalCode;
        this.gender = gender;
    }

    public Patient() {
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Prenotation> getPrenotationList() {
        return prenotationList;
    }

    public void setPrenotationList(List<Prenotation> prenotationList) {
        this.prenotationList = prenotationList;
    }
}
