package com.example.StudioMedico.entities;

import com.example.StudioMedico.entities.recordEnum.BookingENUM;
import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prenotation")
public class Prenotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenotation")
    private Long idPrenotation;

    @Column(name = "prenotation_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "booking_status")
    private BookingENUM statusBooking;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modify_on")
    private LocalDateTime modifyOn;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RecordStatusENUM status;


    @ManyToOne
    @JoinColumn(name = "ext_id_patient", nullable = false)
    @JsonIgnore
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "ext_id_doctor", nullable = false)
    @JsonIgnore
    private Doctor doctor;


    public Prenotation(LocalDateTime date, BookingENUM statusBooking, String createdBy, String modifiedBy, LocalDateTime createdOn, LocalDateTime modifyOn, RecordStatusENUM status, Patient patient, Doctor doctor) {
        this.date = date;
        this.statusBooking = statusBooking;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdOn = createdOn;
        this.modifyOn = modifyOn;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
    }
    public Prenotation(){}

    public Long getIdPrenotation() {
        return idPrenotation;
    }

    public void setIdPrenotation(Long idPrenotation) {
        this.idPrenotation = idPrenotation;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BookingENUM getStatusBooking() {
        return statusBooking;
    }

    public void setStatusBooking(BookingENUM statusBooking) {
        this.statusBooking = statusBooking;
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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(LocalDateTime modifyOn) {
        this.modifyOn = modifyOn;
    }

    public RecordStatusENUM getStatus() {
        return status;
    }

    public void setStatus(RecordStatusENUM status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
