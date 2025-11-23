package com.vit.shams.service;

import com.vit.shams.model.*;

public class AppointmentService {
    private NotificationService notificationService;

    public AppointmentService() {
        this.notificationService = NotificationService.getInstance();
    }

    public Appointment bookAppointment(Patient patient, Doctor doctor, TimeSlot slot) {
        System.out.println("\n=== ATTEMPTING TO BOOK APPOINTMENT ===");
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
        System.out.println("Requested Slot: " + slot.getStartTime());
        
        Appointment appointment = patient.bookAppointment(doctor, slot);
        
        if (appointment != null) {
            // Send notifications using Observer pattern
            String message = "Your appointment with Dr. " + doctor.getName() + 
                           " has been confirmed for " + slot.getStartTime();
            notificationService.notifyAll(message, patient);
            
            // Process billing
            appointment.getBilling().generateInvoice();
        }
        
        return appointment;
    }

    public void cancelAppointment(Patient patient, Appointment appointment) {
        System.out.println("\n=== CANCELLING APPOINTMENT ===");
        patient.cancelAppointment(appointment);
        
        // Send cancellation notifications
        String message = "Your appointment scheduled for " + 
                        appointment.getAppointmentDateTime() + " has been cancelled.";
        notificationService.notifyAll(message, patient);
    }
}