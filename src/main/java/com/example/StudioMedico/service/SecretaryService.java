package com.example.StudioMedico.service;

import com.example.StudioMedico.dto.SecretaryDTO;
import com.example.StudioMedico.entities.Secretary;
import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;
import com.example.StudioMedico.repositories.SecretaryRepository;
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
public class SecretaryService {
    @Autowired
    private SecretaryRepository secretaryRepository;

    /**
     * Creates a new secretary record with status set to active and saves it to the database.
     *
     * @param secretary the Secretary object to be created
     * @return a ResponseEntity with status code 201 and the created Secretary object in the response body
     */
    public ResponseEntity <Secretary> createSecretary(Secretary secretary){
        secretary.setStatus(RecordStatusENUM.A);
        secretary.setCreatedOn(LocalDateTime.now(Clock.systemDefaultZone()));
        secretaryRepository.save(secretary);
        return ResponseEntity.status(201).body(secretary);
    }

    /**
     * Retrieves all active secretaries from the database and returns them as a list.
     *
     * @return a List of all active Secretary objects in the database
     */
    public List<Secretary> getAllSecretary(){
        List<Secretary> allSecretaries = new ArrayList<>();
        secretaryRepository.findAll().forEach(secretary -> {
            if (secretary.getStatus() == RecordStatusENUM.A) {
                allSecretaries.add(secretary);
            }
        });
        return allSecretaries;
    }

    /**
     * Sets the status of the secretary record with the provided ID to deleted and saves it to the database.
     *
     * @param id the ID of the Secretary object to be deleted
     * @return a ResponseEntity with status code 204 if the deletion is successful, or a ResponseEntity with an error message and status code 404 if the provided ID does not match any existing secretary record
     */
    public ResponseEntity deleteSecretary (Long id){
        if (secretaryRepository.existsById(id)) {
            Secretary secretary = secretaryRepository.findById(id).get();
            secretary.setStatus(RecordStatusENUM.D);
            secretaryRepository.saveAndFlush(secretary);
            return new ResponseEntity<>("Succesfully deleted", HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>("no doctor with the id: "+id,HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieves the Secretary object with the provided ID from the database and returns it as an Optional wrapped in a ResponseEntity.
     *
     * @param id the ID of the Secretary object to be retrieved
     * @return a ResponseEntity with status code 200 and the retrieved Secretary object in the response body if it exists in the database, or a ResponseEntity with an error message and status code 404 if the provided ID does not match any existing secretary record
     */
    public ResponseEntity<Optional<Secretary>> getSecretary (Long id){
        if (secretaryRepository.existsById(id)){
            Optional<Secretary> secretary = secretaryRepository.findById(id);
            return ResponseEntity.ok().body(secretary);
        }
        return new ResponseEntity("no secretary exiat with id "+id, HttpStatus.NOT_FOUND);
    }

    /**
     * Updates the Secretary object with the provided ID with the information provided in the SecretaryDTO object and saves it to the database.
     *
     * @param id the ID of the Secretary object to be updated
     * @param secretaryDTO the SecretaryDTO object containing the updated information
     * @return a ResponseEntity with status code 200 and the updated Secretary object in the response body if the update is successful, or a ResponseEntity with status code 404 if the provided ID does not match any existing secretary record
     */
    public ResponseEntity<Secretary> updateSecretary(Long id, SecretaryDTO secretaryDTO){
        Optional<Secretary> secretaryUpdate = secretaryRepository.findById(id);
        if (secretaryUpdate.isPresent()){
            Secretary secretary = secretaryUpdate.get();
            secretary.setName(secretaryDTO.getName());
            secretary.setSurname(secretaryDTO.getSurname());
            secretary.setEmail(secretaryDTO.getEmail());
            secretary.setWorkplace(secretaryDTO.getWorkplace());
            secretary.setModifyOn(LocalDateTime.now(Clock.systemDefaultZone()));

            secretaryRepository.saveAndFlush(secretary);
            return ResponseEntity.ok().body(secretary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Sets the status of all secretary records to deleted and saves them to the database.
     *
     * @return a ResponseEntity with status code 204 if the deletion is successful
     */
    public ResponseEntity deleteAllSecretary(){
        secretaryRepository.findAll().forEach(secretary ->{
            secretary.setStatus(RecordStatusENUM.D);
            secretaryRepository.saveAndFlush(secretary);
        });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}