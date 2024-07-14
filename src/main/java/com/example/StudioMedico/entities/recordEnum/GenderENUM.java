package com.example.StudioMedico.entities.recordEnum;

public enum GenderENUM {
    MALE("Male"),
    FEMALE("Female");

    private String description;

    GenderENUM(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
