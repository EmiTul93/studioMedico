package com.example.StudioMedico.controllers;

import com.example.StudioMedico.dto.PatientDTO;
import com.example.StudioMedico.entities.Patient;
import com.example.StudioMedico.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    //Creating a patient
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }

    //Reading a patient
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatient(@PathVariable Long id){
        return patientService.getPatient(id);
    }

    //Reading all patients
    @GetMapping("/readAll")
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    //Update a patient
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO){
        return patientService.updatePatient(id, patientDTO);
    }

    //Delete a patient
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id){
        return patientService.deletePatient(id);
    }

    //Delete all patients
    @DeleteMapping("/deleteAll")
    public void deleteAllPatients(){
        patientService.deleteAllPatients();
    }

    //Assign a Doctor
    @PatchMapping("/doctor")
    public ResponseEntity<Patient> assignDoctor(@RequestParam Long idPatient, @RequestParam Long idDoctor) {
        return patientService.assignDoctor(idPatient, idDoctor);
    }



}
