package com.example.StudioMedico.service;

import com.example.StudioMedico.dto.PatientDTO;
import com.example.StudioMedico.entities.Patient;
import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;
import com.example.StudioMedico.repositories.DoctorRepository;
import com.example.StudioMedico.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     *
     * Method for creating a new patient.
     * @param patient the patient to create
     * @return an instance of ResponseEntity that encapsulates the created patient and HTTP status 201
     */
    public ResponseEntity<Patient> createPatient(Patient patient){
        patient.setStatus(RecordStatusENUM.A);
        patient.setCreatedOn(LocalDateTime.now(Clock.systemDefaultZone()));
        patientRepository.save(patient);
        return ResponseEntity.status(201).body(patient);
    }

    /**
     * Method for retrieving a specific patient by their ID.
     *
     * @param id the ID of the patient to retrieve
     * @return an instance of ResponseEntity that encapsulates the retrieved patient and HTTP status 200 if the patient exists, otherwise a response with HTTP status 404
     */
    public ResponseEntity<Optional<Patient>> getPatient (Long id) {
        if (patientRepository.existsById(id)) {
            Optional<Patient> patient = patientRepository.findById(id);
            return ResponseEntity.ok().body(patient);
        }
        return new ResponseEntity("no patient exists whith id: " + id, HttpStatus.NOT_FOUND);
    }

    /**
     * Method for retrieving all existing patients.
     *
     * @return a list of active patients
     */
    public List<Patient> getAllPatients(){
        List<Patient> allPatients = new ArrayList<>();
        patientRepository.findAll().forEach(patient -> {
            if (patient.getStatus() == RecordStatusENUM.A) {
                allPatients.add(patient);
            }
        });
        return allPatients;
    }

    /**
     *
     * Method for updating the information of an existing patient.
     *
     * @param id the ID of the patient to update
     * @param patientDTO the new information of the patient
     * @return an instance of ResponseEntity that encapsulates the updated patient and HTTP status 200 if the patient exists, otherwise a response with HTTP status 404
     */
    public ResponseEntity<Patient> updatePatient(Long id, PatientDTO patientDTO) {
        Optional<Patient> patientUpdate = patientRepository.findById(id);
        if (patientUpdate.isPresent()) {
            Patient patient = patientUpdate.get();
            patient.setName(patientDTO.getName());
            patient.setSurname(patientDTO.getSurname());
            patient.setEmail(patientDTO.getEmail());
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
            patient.setFiscalCode(patientDTO.getFiscalCode());
            patient.setGender(patientDTO.getGender());
            patient.setModifyOn(LocalDateTime.now(Clock.systemDefaultZone()));
            patientRepository.saveAndFlush(patient);
            return ResponseEntity.ok().body(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     *
     * Method for deleting an existing patient.
     *
     * @param id the ID of the patient to delete
     * @return an instance of ResponseEntity with a confirmation message and HTTP status 204 if the patient was successfully deleted, otherwise a response with HTTP status 404
     */
    public ResponseEntity deletePatient (Long id) {
        if (patientRepository.existsById(id)) {
            Patient patient = patientRepository.findById(id).get();
            patient.setStatus(RecordStatusENUM.D);
            patientRepository.saveAndFlush(patient);
            return new ResponseEntity("successfully deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity("no patient with the id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method for deleting all patients.
     *
     * @return an instance of ResponseEntity with HTTP status 204
     */
    public ResponseEntity deleteAllPatients() {
        patientRepository.findAll().forEach(patient -> {
            patient.setStatus(RecordStatusENUM.D);
            patientRepository.saveAndFlush(patient);
        });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * Method for assigning a doctor to a patient.
     *
     * @param patientId the ID of the patient to assign the doctor to
     * @param doctorId the ID of the doctor to assign
     * @return an instance of ResponseEntity that encapsulates the updated patient and HTTP status 200 if the patient and doctor exist, otherwise a response with HTTP status 404
     */
    public ResponseEntity<Patient> assignDoctor(Long patientId, Long doctorId) {
        if (patientRepository.existsById(patientId) && doctorRepository.existsById(doctorId)) {
            Patient patient = patientRepository.findById(patientId).get();
            patient.setDoctor(doctorRepository.findById(doctorId).get());
            Patient updatedPatient = patientRepository.saveAndFlush(patient);
            return ResponseEntity.ok(updatedPatient);
        }
        return new ResponseEntity("no patient found with id:" + patientId, HttpStatus.NOT_FOUND);
    }


}
