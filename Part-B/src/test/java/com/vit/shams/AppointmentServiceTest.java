package com.vit.shams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

// Import all model classes
import com.vit.shams.model.Patient;
import com.vit.shams.model.Doctor;
import com.vit.shams.model.Appointment;
import com.vit.shams.model.TimeSlot;
import com.vit.shams.service.AppointmentService;

public class AppointmentServiceTest {
    
    private Patient patient;
    private Doctor doctor;
    private TimeSlot availableSlot;
    private AppointmentService appointmentService;
    
    @BeforeEach
    void setUp() {
        patient = new Patient("PAT001", "John Doe", "john@email.com", "pass123", "1990-01-01", "+61400123456");
        doctor = new Doctor("DOC001", "Dr. Smith", "smith@shams.com", "pass123", "Cardiology", "MED12345");
        availableSlot = new TimeSlot(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        doctor.getSchedule().addTimeSlot(availableSlot);
        appointmentService = new AppointmentService();
    }
    
    @Test
    void testBookAppointment_Success() {
        Appointment appointment = appointmentService.bookAppointment(patient, doctor, availableSlot);
        
        assertNotNull(appointment, "Appointment should be created successfully");
        assertEquals("Scheduled", appointment.getStatus(), "Appointment status should be 'Scheduled'");
        assertEquals(patient, appointment.getPatient(), "Patient should match");
        assertEquals(doctor, appointment.getDoctor(), "Doctor should match");
    }
    
    @Test
    void testDoubleBooking_Prevention() {
        Patient patient2 = new Patient("PAT002", "Jane Smith", "jane@email.com", "pass123", "1985-01-01", "+61400987654");
        
        Appointment appointment1 = appointmentService.bookAppointment(patient, doctor, availableSlot);
        Appointment appointment2 = appointmentService.bookAppointment(patient2, doctor, availableSlot);
        
        assertNotNull(appointment1, "First appointment should be created");
        assertNull(appointment2, "Second appointment should be prevented");
    }
    
    @Test
    void testSlotAvailability() {
        assertTrue(doctor.getSchedule().isSlotAvailable(availableSlot), "Slot should be available initially");
        
        appointmentService.bookAppointment(patient, doctor, availableSlot);
        
        assertFalse(doctor.getSchedule().isSlotAvailable(availableSlot), "Slot should be unavailable after booking");
    }
}