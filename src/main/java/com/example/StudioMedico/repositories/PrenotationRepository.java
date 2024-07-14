package com.example.StudioMedico.repositories;

import com.example.StudioMedico.entities.Prenotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PrenotationRepository extends JpaRepository <Prenotation, Long> {

    List<Prenotation> findByDoctor_IdDoctorAndDateBetween(Long idDoctor, LocalDateTime startTime, LocalDateTime endTime);
}