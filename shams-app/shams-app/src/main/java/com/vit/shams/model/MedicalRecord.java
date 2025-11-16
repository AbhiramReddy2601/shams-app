package com.vit.shams.model;

public class MedicalRecord {
    private String recordId;
    private String date;
    private String diagnosis;
    private String prescription;

    public MedicalRecord(String recordId, String date, String diagnosis, String prescription) {
        this.recordId = recordId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public void addNotes(String notes) {
        this.diagnosis += "\nAdditional Notes: " + notes;
    }

    // Getters and setters
    public String getRecordId() { return recordId; }
    public String getDate() { return date; }
    public String getDiagnosis() { return diagnosis; }
    public String getPrescription() { return prescription; }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", date='" + date + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                '}';
    }
}