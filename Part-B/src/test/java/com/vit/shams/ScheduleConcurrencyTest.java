package com.vit.shams;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// Import model and service classes
import com.vit.shams.model.Patient;
import com.vit.shams.model.Doctor;
import com.vit.shams.model.Appointment;
import com.vit.shams.model.TimeSlot;
import com.vit.shams.service.AppointmentService;

public class ScheduleConcurrencyTest {
    
    @Test
    void testConcurrentBooking() throws InterruptedException {
        Doctor doctor = new Doctor("DOC001", "Dr. Smith", "smith@shams.com", "pass123", "Cardiology", "MED12345");
        TimeSlot slot = new TimeSlot(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1));
        doctor.getSchedule().addTimeSlot(slot);
        
        int numberOfThreads = 5;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger successfulBookings = new AtomicInteger(0);
        AtomicInteger failedBookings = new AtomicInteger(0);
        
        for (int i = 0; i < numberOfThreads; i++) {
            final int patientId = i;
            service.submit(() -> {
                try {
                    Patient patient = new Patient("PAT" + patientId, "Patient " + patientId, 
                                                "patient" + patientId + "@email.com", "pass123", 
                                                "1990-01-01", "+6140000000" + patientId);
                    AppointmentService appointmentService = new AppointmentService();
                    Appointment appointment = appointmentService.bookAppointment(patient, doctor, slot);
                    
                    if (appointment != null) {
                        successfulBookings.incrementAndGet();
                    } else {
                        failedBookings.incrementAndGet();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        latch.await();
        service.shutdown();
        
        assertEquals(1, successfulBookings.get(), "Only one booking should succeed");
        assertEquals(4, failedBookings.get(), "Four bookings should fail due to concurrency control");
    }
}