package com.example.StudioMedico.service;

import com.example.StudioMedico.dto.DoctorDTO;
import com.example.StudioMedico.entities.Doctor;
import com.example.StudioMedico.entities.Patient;
import com.example.StudioMedico.entities.Prenotation;
import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;
import com.example.StudioMedico.repositories.DoctorRepository;
import com.example.StudioMedico.repositories.SecretaryRepository;
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
/**

 This class provides services related to doctors, such as creating, retrieving, updating and deleting doctors.
 */
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private SecretaryRepository secretaryRepository;

    /**

     Creates a new doctor with the given information.
     @param doctor The doctor object containing the information of the new doctor to create.
     @return A ResponseEntity object containing the HTTP status code and the created doctor object.
     */
    public ResponseEntity<Doctor> createDoctor(Doctor doctor){
        doctor.setStatus(RecordStatusENUM.A);
        doctor.setCreatedOn(LocalDateTime.now(Clock.systemDefaultZone()));
        doctorRepository.save(doctor);
        return ResponseEntity.status(201).body(doctor);
    }

    /**

     Retrieves a list of all doctors.
     @return A list of Doctor objects.
     */
    public List<Doctor> getAllDoctors(){
        List<Doctor> alldocs = new ArrayList<>();
        doctorRepository.findAll().forEach(doctor -> {
            if (doctor.getStatus() == RecordStatusENUM.A) {
                alldocs.add(doctor);
            }
        });
        return alldocs;
    }
    /**

     Deletes a doctor with the specified id.
     @param id The id of the doctor to delete.
     @return A ResponseEntity object containing the HTTP status code and a success or error message.
     */
    public ResponseEntity deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            Doctor doctor = doctorRepository.findById(id).get();
            doctor.setStatus(RecordStatusENUM.D);
            doctorRepository.saveAndFlush(doctor);
            return new ResponseEntity("succesfully deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity("no doctor with the id: " + id, HttpStatus.NOT_FOUND);
        }
    }
    /**

     Retrieves the doctor with the specified id.
     @param id The id of the doctor to retrieve.
     @return A ResponseEntity object containing the HTTP status code and an Optional Doctor object.
     */
    public ResponseEntity<Optional<Doctor>> getDoctor(Long id){
        if (doctorRepository.existsById(id)) {
            Optional<Doctor> doctor = doctorRepository.findById(id);
            return ResponseEntity.ok().body(doctor);
        }
        return new ResponseEntity("no doctor exist with id " + id, HttpStatus.NOT_FOUND);
    }


    /**

     Updates the doctor with the specified id using the information from the provided DoctorDTO object.

     @param id The id of the doctor to update.

     @param doctorDTO The DoctorDTO object containing the updated information for the doctor.

     @return A ResponseEntity object containing the HTTP status code and the updated Doctor object.
     */
    public ResponseEntity<Doctor> updateDoctor(Long id, DoctorDTO doctorDTO){
        Optional<Doctor> doctorUpdate = doctorRepository.findById(id);
        if (doctorUpdate.isPresent()) {
            Doctor doctor = doctorUpdate.get();

            doctor.setName(doctorDTO.getName());
            doctor.setSurname(doctorDTO.getSurname());
            doctor.setEmail(doctorDTO.getEmail());
            doctor.setWorkplace(doctorDTO.getWorkplace());
            doctor.setSpecialization(doctorDTO.getSpecialization());
            doctor.setModifyOn(LocalDateTime.now(Clock.systemDefaultZone()));

            doctorRepository.saveAndFlush(doctor);
            return ResponseEntity.ok().body(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     * Deletes all doctors by setting their status to 'D' (deleted).
     *
     * @return a ResponseEntity with status code HttpStatus.NO_CONTENT upon successful deletion.
     */
    public ResponseEntity deleteAllDoctor(){
        doctorRepository.findAll().forEach(doctor -> {
            doctor.setStatus(RecordStatusENUM.D);
            doctorRepository.saveAndFlush(doctor);
        });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Assigns a secretary to a doctor.
     *
     * @param idDoctor the ID of the doctor to whom the secretary should be assigned
     * @param idSecretary the ID of the secretary to be assigned to the doctor
     * @return a ResponseEntity with the updated doctor object upon successful assignment, or a ResponseEntity
     * with status code HttpStatus.NOT_FOUND if the doctor or secretary with the specified ID does not exist.
     */
    public ResponseEntity<Doctor> secretaryDoc(Long idDoctor, Long idSecretary){
        if (secretaryRepository.existsById(idSecretary)) {
            Doctor doctor = doctorRepository.findById(idDoctor).get();
            doctor.setSecretary(secretaryRepository.findById(idSecretary).get());
            Doctor updateDoctor = doctorRepository.saveAndFlush(doctor);
            return ResponseEntity.ok(updateDoctor);
        }
        return new ResponseEntity("no doctor found with id " + idDoctor, HttpStatus.NOT_FOUND);
    }

    /**
     * Return all doctor's prenotation by id
     *
     * @param id
     * @return prenotationListById;
     */
    public List<Prenotation> getAllDoctorPrenotation(Long id) throws Exception {
        if (doctorRepository.existsById(id)) {
            List<Prenotation> doctorActivePrenotation = new ArrayList<>();
            doctorRepository.findById(id).get().getPrenotationList().forEach(prenotation -> {
                if (prenotation.getStatus() == RecordStatusENUM.A) {
                    doctorActivePrenotation.add(prenotation);
                }
            });
            return doctorActivePrenotation;
        } else throw new Exception("this doctor does not exist");
    }

    /**
     * Return doctor's patient by id
     *
     * @param doctorId
     * @return patientListById
     */

    public List<Patient> getDoctorPatientList(Long doctorId) throws Exception {
        if (doctorRepository.existsById(doctorId)) {
            List<Patient> activePatientList = new ArrayList<>();
            doctorRepository.findById(doctorId).get().getPatientList().forEach(patient -> {
                if (patient.getStatus() == RecordStatusENUM.A) {
                    activePatientList.add(patient);
                }
            });
            return activePatientList;
        } else throw new Exception("this doctor does not exist");
    }

}
