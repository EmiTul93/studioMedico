package com.example.StudioMedico.entities.recordEnum;

public enum RecordStatusENUM {
    A("Active"),
    D("Deleted");
    private String description;

    RecordStatusENUM(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
