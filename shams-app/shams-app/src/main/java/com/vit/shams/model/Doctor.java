package com.vit.shams.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private String specialization;
    private String licenseNumber;
    private Schedule schedule;
    private List<Appointment> appointments;

    public Doctor(String userId, String name, String email, String password,
                  String specialization, String licenseNumber) {
        super(userId, name, email, password);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.schedule = new Schedule("SCH" + userId);
        this.appointments = new ArrayList<>();
    }

    public void viewSchedule() {
        System.out.println("Schedule for Dr. " + name + " (" + specialization + "):");
        schedule.displayAvailableSlots();
    }

    public void updateAvailability(TimeSlot slot) {
        schedule.addTimeSlot(slot);
        System.out.println("Availability updated for: " + slot.getStartTime());
    }

    public void consultPatient(Appointment appointment) {
        System.out.println("Consulting patient: " + appointment.getPatient().getName());
        appointment.setStatus("Completed");
        
        // Create medical record after consultation
        MedicalRecord record = new MedicalRecord(
            "MR" + System.currentTimeMillis(),
            appointment.getAppointmentDateTime().toString(),
            "Follow-up required",
            "Prescription medication"
        );
        appointment.getPatient().addMedicalRecord(record);
    }

    // Getters and setters
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    public List<Appointment> getAppointments() { return appointments; }
    public void addAppointment(Appointment appointment) { appointments.add(appointment); }
}