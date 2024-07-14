package com.example.StudioMedico.dto;

import com.example.StudioMedico.entities.recordEnum.BookingENUM;
import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrenotationDTO {
    private Long idPrenotation;
    private LocalDateTime date;
    private BookingENUM bookingStatus;
    private Long patientId;
    private Long doctorId;
    private String createdBy;

    private String modifiedBy;

    private LocalDate createdOn;

    private LocalDate modifyOn;
    private RecordStatusENUM status;

    public PrenotationDTO(LocalDateTime date, BookingENUM bookingStatus, Long patientId, Long doctorId, String createdBy, String modifiedBy, LocalDate createdOn, LocalDate modifyOn,RecordStatusENUM status) {
        this.date = date;
        this.bookingStatus = bookingStatus;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdOn = createdOn;
        this.modifyOn = modifyOn;
        this.status = status;
    }

    public PrenotationDTO(){}

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

    public BookingENUM getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingENUM bookingStatus) {
        this.bookingStatus = bookingStatus;
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

    public RecordStatusENUM getStatus() {
        return status;
    }

    public void setStatus(RecordStatusENUM status) {
        this.status = status;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
