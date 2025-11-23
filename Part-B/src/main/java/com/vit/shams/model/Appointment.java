package com.vit.shams.model;

import java.time.LocalDateTime;

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentDateTime;
    private String status;
    private String notes;
    private Billing billing;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, 
                      TimeSlot timeSlot, String status) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDateTime = timeSlot.getStartTime();
        this.status = status;
        this.notes = "";
        this.billing = new Billing("BIL" + appointmentId, 150.00, "Pending");
    }

    public void confirm() {
        this.status = "Confirmed";
        System.out.println("Appointment confirmed: " + appointmentId);
    }

    public void cancel() {
        this.status = "Cancelled";
        // Release the time slot
        doctor.getSchedule().releaseSlot(
            new TimeSlot(appointmentDateTime, appointmentDateTime.plusHours(1))
        );
        System.out.println("Appointment cancelled: " + appointmentId);
    }

    public void reschedule(LocalDateTime newDateTime) {
        // Release old slot
        doctor.getSchedule().releaseSlot(
            new TimeSlot(appointmentDateTime, appointmentDateTime.plusHours(1))
        );
        
        // Try to reserve new slot
        TimeSlot newSlot = new TimeSlot(newDateTime, newDateTime.plusHours(1));
        if (doctor.getSchedule().reserveSlot(newSlot)) {
            this.appointmentDateTime = newDateTime;
            this.status = "Rescheduled";
            System.out.println("Appointment rescheduled to: " + newDateTime);
        } else {
            System.out.println("Cannot reschedule - slot not available.");
        }
    }

    public void addNotes(String notes) {
        this.notes = notes;
    }

    // Getters and setters
    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public Billing getBilling() { return billing; }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patient=" + patient.getName() +
                ", doctor=" + doctor.getName() +
                ", dateTime=" + appointmentDateTime +
                ", status='" + status + '\'' +
                '}';
    }
}