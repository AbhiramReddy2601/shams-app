package com.vit.shams.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private String scheduleId;
    private List<TimeSlot> availableSlots;

    public Schedule(String scheduleId) {
        this.scheduleId = scheduleId;
        this.availableSlots = new ArrayList<>();
    }

    public synchronized boolean isSlotAvailable(TimeSlot requestedSlot) {
        for (TimeSlot slot : availableSlots) {
            if (slot.equals(requestedSlot) && slot.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean reserveSlot(TimeSlot slotToReserve) {
        for (TimeSlot slot : availableSlots) {
            if (slot.equals(slotToReserve) && slot.isAvailable()) {
                slot.setAvailable(false);
                System.out.println("Slot reserved: " + slot.getStartTime());
                return true;
            }
        }
        return false;
    }

    public synchronized void releaseSlot(TimeSlot slotToRelease) {
        for (TimeSlot slot : availableSlots) {
            if (slot.equals(slotToRelease)) {
                slot.setAvailable(true);
                System.out.println("Slot released: " + slot.getStartTime());
                break;
            }
        }
    }

    public void addTimeSlot(TimeSlot slot) {
        availableSlots.add(slot);
    }

    public void displayAvailableSlots() {
        System.out.println("Available Slots:");
        for (TimeSlot slot : availableSlots) {
            if (slot.isAvailable()) {
                System.out.println("  - " + slot.getStartTime() + " to " + slot.getEndTime());
            }
        }
    }

    public boolean hasAvailableSlot(String date) {
        return availableSlots.stream()
                .anyMatch(slot -> slot.isAvailable() && 
                         slot.getStartTime().toLocalDate().toString().equals(date));
    }

    // Getters
    public String getScheduleId() { return scheduleId; }
    public List<TimeSlot> getAvailableSlots() { return availableSlots; }
}