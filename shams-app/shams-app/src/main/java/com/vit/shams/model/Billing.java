package com.vit.shams.model;

public class Billing {
    private String billingId;
    private double amount;
    private String status;

    public Billing(String billingId, double amount, String status) {
        this.billingId = billingId;
        this.amount = amount;
        this.status = status;
    }

    public void generateInvoice() {
        System.out.println("=== INVOICE ===");
        System.out.println("Billing ID: " + billingId);
        System.out.println("Amount: $" + amount);
        System.out.println("Status: " + status);
        System.out.println("===============");
    }

    public void processPayment() {
        this.status = "Paid";
        System.out.println("Payment processed for billing ID: " + billingId);
    }

    // Getters and setters
    public String getBillingId() { return billingId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}