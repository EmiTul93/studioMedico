package com.example.StudioMedico.controllers;

import com.example.StudioMedico.dto.PrenotationDTO;
import com.example.StudioMedico.entities.Prenotation;
import com.example.StudioMedico.service.PrenotationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prenotation")
public class PrenotationController {

    @Autowired
    private PrenotationService prenotationService;
    //Create
    @PostMapping("/create")
    public ResponseEntity<Prenotation> create(@RequestBody Prenotation prenotation, @RequestParam Long idDoctor, @RequestParam Long idPatient){
        return prenotationService.createPrenotation(prenotation, idDoctor, idPatient);
    }

    // Read All
    @GetMapping("/allprenotations")
    public List<Prenotation> getAllPrenotations(){
        return prenotationService.getAllPrenotations();
    }

    // Update
    @PutMapping("/Status/{id}")
    public ResponseEntity<Prenotation> updatePrenotation(@PathVariable Long id, @RequestBody PrenotationDTO prenotationDto) {
        return prenotationService.updatePrenotation(id, prenotationDto);
    }

    // Delete all
    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        prenotationService.deleteAllPrenotations();
    }

    // Read One
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Prenotation>> getPrenotationById(@PathVariable Long id) {
        return prenotationService.getPrenotationById(id);
    }

    // Delete One
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePrenotationById(@PathVariable long id) {
        return prenotationService.deletePrenotationById(id);
    }
}
