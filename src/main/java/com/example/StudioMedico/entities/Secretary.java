package com.example.StudioMedico.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "secretary")
public class Secretary extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_secretary")
    private Long idSecretary;

    @Column(name = "workplace", nullable = false)
    private String workplace;

    @OneToMany(mappedBy = "secretary")
    @JsonIgnore
    private List<Doctor> doctorList;


    public Secretary(String name, String surname, String email, String workplace) {
        super(name, surname, email);
        this.workplace=workplace;
    }

    public Secretary() {
        super();
    }

    public Long getIdSecretary() {
        return idSecretary;
    }

    public void setIdSecretary(Long idSecretary) {
        this.idSecretary = idSecretary;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
