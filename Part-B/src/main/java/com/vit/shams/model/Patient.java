package com.vit.shams.model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private String dateOfBirth;
    private String contactNumber;
    private List<Appointment> appointments;
    private List<MedicalRecord> medicalRecords;

    public Patient(String userId, String name, String email, String password, 
                   String dateOfBirth, String contactNumber) {
        super(userId, name, email, password);
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.appointments = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
    }

    public List<Doctor> searchDoctors(List<Doctor> doctors, String specialty, String date) {
        List<Doctor> matchedDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialty) && 
                doctor.getSchedule().hasAvailableSlot(date)) {
                matchedDoctors.add(doctor);
            }
        }
        return matchedDoctors;
    }

    public Appointment bookAppointment(Doctor doctor, TimeSlot slot) {
        if (doctor.getSchedule().reserveSlot(slot)) {
            Appointment appointment = new Appointment(
                "APT" + System.currentTimeMillis(),
                this,
                doctor,
                slot,
                "Scheduled"
            );
            
            this.appointments.add(appointment);
            doctor.addAppointment(appointment);
            
            System.out.println("Appointment booked successfully for: " + slot.getStartTime());
            return appointment;
        } else {
            System.out.println("Sorry, the selected time slot is no longer available.");
            return null;
        }
    }

    public void cancelAppointment(Appointment appointment) {
        if (appointments.contains(appointment)) {
            appointment.cancel();
            appointments.remove(appointment);
            System.out.println("Appointment cancelled successfully.");
        }
    }

    public void viewMedicalHistory() {
        System.out.println("Medical History for " + name + ":");
        for (MedicalRecord record : medicalRecords) {
            System.out.println(record);
        }
    }

    // Getters and setters
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public List<Appointment> getAppointments() { return appointments; }
    public List<MedicalRecord> getMedicalRecords() { return medicalRecords; }

    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }
}