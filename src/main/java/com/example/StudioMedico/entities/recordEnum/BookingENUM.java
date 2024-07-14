package com.example.StudioMedico.entities.recordEnum;

public enum BookingENUM {
    CONFIRMED("Booking Confirmed"),
    CANCELLED("Booking Cancelled");
    private String description;

    BookingENUM(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}