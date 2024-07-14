package com.example.StudioMedico.dto;



import com.example.StudioMedico.entities.recordEnum.RecordStatusENUM;

import java.time.LocalDate;

public class SecretaryDTO extends UserDTO {
    private Long idSecretary;
    private String workplace;

    public SecretaryDTO(String name, String surname, String email, String createdBy, String modifiedBy, LocalDate createdOn, LocalDate modifyOn, RecordStatusENUM status, String workplace) {
        super(name, surname, email, createdBy, modifiedBy, createdOn, modifyOn,status);
        this.workplace=workplace;
    }

    public SecretaryDTO() {
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
}
