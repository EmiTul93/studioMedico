package com.example.StudioMedico.service;

import com.example.StudioMedico.dto.PrenotationDTO;
import com.example.StudioMedico.entities.Prenotation;
import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;
import com.example.StudioMedico.repositories.DoctorRepository;
import com.example.StudioMedico.repositories.PatientRepository;
import com.example.StudioMedico.repositories.PrenotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotationService {
    @Autowired
    PrenotationRepository prenotationRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    /**
     * Creates a new prenotation with the given doctor and patient IDs.
     * Checks if the doctor and patient exist, sets their references in the prenotation object.
     * Checks if there is any prenotation with the same doctor and time within 30 minutes, if so, returns CONFLICT status.
     * Sets the prenotation status to 'A', and saves it to the database.
     * @param prenotation The prenotation object to create.
     * @param idDoctor The ID of the doctor associated with the prenotation.
     * @param idPatient The ID of the patient associated with the prenotation.
     * @return A ResponseEntity with the saved prenotation object and status OK, or an error message with status NOT_FOUND or CONFLICT.
     */
    public ResponseEntity<Prenotation> createPrenotation(Prenotation prenotation, Long idDoctor, Long idPatient) {
        if (doctorRepository.existsById(idDoctor)) {
            prenotation.setDoctor(doctorRepository.getReferenceById(idDoctor));
        } else {
            return new ResponseEntity("doctor not found", HttpStatus.NOT_FOUND);
        }
        if (patientRepository.existsById(idPatient)) {
            prenotation.setPatient(patientRepository.getReferenceById(idPatient));
        } else {
            return new ResponseEntity("patient not found", HttpStatus.NOT_FOUND);
        }
        LocalDateTime startTime = prenotation.getDate();
        LocalDateTime endTime = startTime.plusMinutes(30);
        List<Prenotation> prenotations = prenotationRepository.findByDoctor_IdDoctorAndDateBetween(idDoctor, startTime, endTime);
        if (!prenotations.isEmpty()) {
            return new ResponseEntity("there is already a prenotation within 30 minutes", HttpStatus.CONFLICT);
        }
        prenotation.setStatus(RecordStatusENUM.A);
        prenotation.setCreatedOn(LocalDateTime.now(Clock.systemDefaultZone()));
        return ResponseEntity.ok(prenotationRepository.save(prenotation));
    }



    /**
     * Updates an existing prenotation with the given ID.
     * If the prenotation exists, updates its date, booking status, doctor, and patient fields based on the DTO object.
     * Sets the modifyOn field to the current time and saves the updated prenotation to the database.
     * @param id The ID of the prenotation to update.
     * @param prenotationDto The DTO object containing the updated fields.
     * @return A ResponseEntity with the updated prenotation object and status OK, or an error message with status NOT_FOUND.
     */
    public ResponseEntity<Prenotation> updatePrenotation(long id, PrenotationDTO prenotationDto) {
        if (prenotationRepository.existsById(id)) {
            Prenotation prenotation = prenotationRepository.findById(id).get();
            if (prenotationDto.getDate() != null) {
                prenotation.setDate(prenotationDto.getDate());
            }
            if (prenotationDto.getBookingStatus() != null) {
                prenotation.setStatusBooking(prenotationDto.getBookingStatus());
            }
            if (prenotationDto.getDoctorId() != null) {
                prenotation.setDoctor(doctorRepository.getReferenceById(prenotationDto.getDoctorId()));
            }
            if (prenotationDto.getPatientId() != null) {
                prenotation.setPatient(patientRepository.getReferenceById(prenotationDto.getPatientId()));
            }
            prenotation.setModifyOn(LocalDateTime.now(Clock.systemDefaultZone()));
            Prenotation updatedPrenotation = prenotationRepository.saveAndFlush(prenotation);
            return ResponseEntity.ok(updatedPrenotation);
        }
        return new ResponseEntity("Prenotation with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Returns a list of all active prenotations in the database.
     * @return A list of all active prenotations.
     */
    public List<Prenotation> getAllPrenotations(){
        List<Prenotation> allprenotations = new ArrayList<>();
        prenotationRepository.findAll().forEach(prenotation -> {
            if (prenotation.getStatus() == RecordStatusENUM.A) {
                allprenotations.add(prenotation);
            }
        });
        return allprenotations;
    }

    /**
     * Deletes all prenotations in the database by setting their status to 'D'.
     * @return A ResponseEntity with status NO_CONTENT.
     */
    public ResponseEntity deleteAllPrenotations(){
        prenotationRepository.findAll().forEach(prenotation -> {
            prenotation.setStatus(RecordStatusENUM.D);
            prenotationRepository.saveAndFlush(prenotation);
        });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    /**
     * Returns the prenotation with the given ID if it exists.
     * @param id The ID of the prenotation to retrieve.
     * @return A ResponseEntity with the prenotation object and status OK, or an error message with status NOT_FOUND.
     */
    public ResponseEntity<Optional<Prenotation>> getPrenotationById(long id) {
        if (prenotationRepository.existsById(id)) {
            return ResponseEntity.ok().body(prenotationRepository.findById(id));
        } else {
            return new ResponseEntity("Prenotation with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Deletes the prenotation with the given ID by setting its status to 'D'.
     * @param id The ID of the prenotation to delete.
     * @return A ResponseEntity with status NO_CONTENT, or an error message with status NOT_FOUND.
     */
    public ResponseEntity deletePrenotationById(long id) {
        if (prenotationRepository.existsById(id)) {
            Prenotation prenotation = prenotationRepository.findById(id).get();
            prenotation.setStatus(RecordStatusENUM.D);
            prenotationRepository.saveAndFlush(prenotation);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity("Prenotation with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
