package com.vit.shams;

import com.vit.shams.model.*;
import com.vit.shams.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SHAMS - Smart Healthcare Appointment Management System ===");
        System.out.println("=== Demonstrating Complex Module: Appointment Booking with Notification Orchestration ===\n");

        // Initialize sample data
        List<Doctor> doctors = initializeDoctors();
        List<Patient> patients = initializePatients();
        
        // Get services
        AppointmentService appointmentService = new AppointmentService();

        // Demonstrate the complex module
        demonstrateAppointmentBooking(patients, doctors, appointmentService);
        
        // Demonstrate concurrency control
        demonstrateConcurrencyControl(patients, doctors, appointmentService);
        
        // Demonstrate additional features
        demonstrateAdditionalFeatures(patients, doctors);
    }

    private static List<Doctor> initializeDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        
        Doctor cardiologist = new Doctor("DOC001", "Dr. Smith", "smith@shams.com", "pass123", 
                                        "Cardiology", "MED12345");
        Doctor neurologist = new Doctor("DOC002", "Dr. Johnson", "johnson@shams.com", "pass123", 
                                       "Neurology", "MED12346");
        
        // Set up schedules
        LocalDateTime baseTime = LocalDateTime.now().plusDays(1).withHour(9).withMinute(0);
        
        for (int i = 0; i < 4; i++) {
            LocalDateTime start = baseTime.plusHours(i * 2);
            LocalDateTime end = start.plusHours(1);
            cardiologist.getSchedule().addTimeSlot(new TimeSlot(start, end));
            neurologist.getSchedule().addTimeSlot(new TimeSlot(start, end));
        }
        
        doctors.add(cardiologist);
        doctors.add(neurologist);
        
        System.out.println("Initialized " + doctors.size() + " doctors with schedules");
        return doctors;
    }

    private static List<Patient> initializePatients() {
        List<Patient> patients = new ArrayList<>();
        
        patients.add(new Patient("PAT001", "John Doe", "john@email.com", "pass123", 
                               "1990-05-15", "+61400123456"));
        patients.add(new Patient("PAT002", "Jane Smith", "jane@email.com", "pass123", 
                               "1985-08-22", "+61400987654"));
        
        System.out.println("Initialized " + patients.size() + " patients");
        return patients;
    }

    private static void demonstrateAppointmentBooking(List<Patient> patients, List<Doctor> doctors, 
                                                     AppointmentService appointmentService) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMONSTRATION 1: BASIC APPOINTMENT BOOKING WITH NOTIFICATIONS");
        System.out.println("=".repeat(70));
        
        Patient patient = patients.get(0);
        Doctor doctor = doctors.get(0); // Cardiologist
        
        // Show available slots
        System.out.println("\nAvailable slots for " + doctor.getName() + ":");
        doctor.getSchedule().displayAvailableSlots();
        
        // Book appointment
        TimeSlot slot = doctor.getSchedule().getAvailableSlots().get(0);
        Appointment appointment = appointmentService.bookAppointment(patient, doctor, slot);
        
        if (appointment != null) {
            System.out.println("\n✓ Appointment successfully created: " + appointment);
        }
    }

    private static void demonstrateConcurrencyControl(List<Patient> patients, List<Doctor> doctors,
                                                     AppointmentService appointmentService) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMONSTRATION 2: CONCURRENCY CONTROL (PREVENTING DOUBLE-BOOKING)");
        System.out.println("=".repeat(70));
        
        Doctor doctor = doctors.get(0); // Cardiologist
        Patient patient1 = patients.get(0);
        Patient patient2 = patients.get(1);
        
        // Get the same time slot for both patients
        TimeSlot slot = doctor.getSchedule().getAvailableSlots().get(1);
        
        System.out.println("\nPatient 1 attempting to book slot: " + slot.getStartTime());
        Appointment app1 = appointmentService.bookAppointment(patient1, doctor, slot);
        
        System.out.println("\nPatient 2 attempting to book the same slot: " + slot.getStartTime());
        Appointment app2 = appointmentService.bookAppointment(patient2, doctor, slot);
        
        if (app2 == null) {
            System.out.println("✓ Concurrency control successful: Second booking prevented");
        }
    }

    private static void demonstrateAdditionalFeatures(List<Patient> patients, List<Doctor> doctors) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DEMONSTRATION 3: ADDITIONAL FEATURES");
        System.out.println("=".repeat(70));
        
        Patient patient = patients.get(0);
        Doctor doctor = doctors.get(0);
        
        // Show patient's appointments
        System.out.println("\nPatient's appointments:");
        for (Appointment apt : patient.getAppointments()) {
            System.out.println("  - " + apt);
        }
        
        // Demonstrate consultation and medical records
        if (!patient.getAppointments().isEmpty()) {
            Appointment appointment = patient.getAppointments().get(0);
            System.out.println("\nSimulating consultation for appointment: " + appointment.getAppointmentId());
            doctor.consultPatient(appointment);
            
            System.out.println("\nPatient's medical records after consultation:");
            patient.viewMedicalHistory();
        }
        
        // Demonstrate billing
        if (!patient.getAppointments().isEmpty()) {
            System.out.println("\nProcessing payment for appointment:");
            patient.getAppointments().get(0).getBilling().processPayment();
        }
    }
}