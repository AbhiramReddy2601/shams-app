# SHAMS - Smart Healthcare Appointment Management System

## Project Overview

SHAMS is a comprehensive healthcare appointment management system
demonstrating advanced software engineering principles including
Object-Oriented Design, Design Patterns, and Layered Architecture.

**Complex Module**: Appointment Booking with Notification Orchestration

------------------------------------------------------------------------

## 📋 Table of Contents

1.  [Prerequisites](#prerequisites)
2.  [Quick Start](#quick-start)
3.  [Detailed Run Instructions](#detailed-run-instructions)
4.  [Sample Data & Expected Output](#sample-data--expected-output)
5.  [Developer Guide](#developer-guide)
6.  [Project Structure](#project-structure)
7.  [Testing Instructions](#testing-instructions)
8.  [Design Patterns](#design-patterns)
9.  [Troubleshooting](#troubleshooting)

------------------------------------------------------------------------

## Prerequisites

### System Requirements

-   **OS**: Windows 10/11, macOS 10.14+, or Linux (Ubuntu 18.04+)
-   **Java**: JDK 17 or higher
-   **Disk Space**: 10 MB minimum
-   **Memory**: 512 MB RAM minimum

### Java Installation Check

``` bash
java -version
javac -version
```

Expected Output:

    java version "17.0.9"  
    Java(TM) SE Runtime Environment  
    Java HotSpot(TM) 64-Bit Server VM

------------------------------------------------------------------------

## Quick Start

### For Windows Users:

1.  Download and extract the project\
2.  Double-click **run-shams.bat**\
3.  Wait for compilation and observe demo output

### For Linux/macOS Users:

``` bash
chmod +x run-shams.sh
./run-shams.sh
```

------------------------------------------------------------------------

## Manual Execution:

``` bash
cd shams-app

javac -d out src/main/java/com/vit/shams/model/*.java src/main/java/com/vit/shams/observer/*.java src/main/java/com/vit/shams/factory/*.java src/main/java/com/vit/shams/service/*.java src/main/java/com/vit/shams/Main.java

java -cp out com.vit.shams.Main
```

------------------------------------------------------------------------

## Detailed Run Instructions

### Step-by-Step for Markers:

#### 1. Environment Setup

``` cmd
java -version
cd C:\Users\Marker\Downloads\shams-app
```

#### 2. Compilation & Execution

``` cmd
run-shams.bat
```

OR manual:

``` cmd
mkdir out
javac -d out src\main\java\com\vit\shams\**\*.java
java -cp out com.vit.shams.Main
```

------------------------------------------------------------------------

## Sample Data & Expected Output

### Pre-loaded Sample Data:

**Doctors:** - Dr. Smith --- Cardiology\
- Dr. Johnson --- Neurology

**Patients:** - John Doe\
- Jane Smith

**Time Slots:**\
- 9AM, 11AM, 1PM, 3PM (per doctor)

------------------------------------------------------------------------

## Expected Demonstration Flow

### **Demo 1: Appointment Booking**

-   Appointment booked\
-   Email notification sent\
-   SMS notification sent\
-   Billing invoice created

### **Demo 2: Concurrency Control**

-   Patient 1 books a slot\
-   Patient 2 tries the same slot → prevented\
-   Output: **"Concurrency control successful"**

### **Demo 3: Additional Features**

-   Appointment list\
-   Consultation simulation\
-   Medical record generation\
-   Payment processing

------------------------------------------------------------------------

## Developer Guide

### Core Package Structure

    src/main/java/com/vit/shams/
    ├── model/
    ├── observer/
    ├── factory/
    ├── service/
    └── Main.java

------------------------------------------------------------------------

## Key Class Implementations

### **1. Domain Models**

-   `User.java` -- Abstract base class\
-   `Patient.java` -- Appointment actions\
-   `Doctor.java` -- Schedule + consultations\
-   `Appointment.java` -- Booking entity\
-   `Schedule.java` -- Concurrency control

### **2. Observer Pattern**

-   `NotificationObserver.java`\
-   `EmailNotification.java`\
-   `SMSNotification.java`

### **3. Factory Pattern**

-   `NotificationFactory.java`

### **4. Business Services**

-   `NotificationService.java` -- Singleton\
-   `AppointmentService.java` -- Workflow orchestration

### **5. Entry Point**

-   `Main.java` -- Runs full demo

------------------------------------------------------------------------

## Project Structure

    shams-app/
    ├── src/
    │   └── main/
    │       └── java/
    │           └── com/vit/shams/
    │               ├── model/
    │               ├── observer/
    │               ├── factory/
    │               ├── service/
    │               └── Main.java
    ├── out/
    ├── run-shams.bat
    ├── run-shams.sh
    └── README.md

------------------------------------------------------------------------

## Testing Instructions

### **1. Compilation Test**

``` cmd
javac -d out src\main\java\com\vit\shams\**\*.java
```

### **2. Execution Test**

``` cmd
java -cp out com.vit.shams.Main
```

### **3. Feature Tests**

-   Email + SMS notifications\
-   Double-booking prevention\
-   Medical records\
-   Billing

------------------------------------------------------------------------

## Design Patterns

  --------------------------------------------------------------------------
  Pattern            Implementation                      Purpose
  ------------------ ----------------------------------- -------------------
  **Observer**       NotificationObserver,               Decoupled
                     NotificationService                 notifications

  **Factory**        NotificationFactory                 Centralized object
                                                         creation

  **Singleton**      NotificationService.getInstance()   One instance
                                                         globally

  **Polymorphism**   NotificationObserver interface      Multiple
                                                         notification types

  **Inheritance**    User → Patient/Doctor               Code reuse
  --------------------------------------------------------------------------

------------------------------------------------------------------------

## Troubleshooting

### Common Issues:

-   `"javac not found"` → Install JDK 17+
-   `"Class not found"` → Ensure out folder exists
-   Wrong output → Use Java 17+, not 8 or lower

### Quick Fix:

``` cmd
rmdir /s /q out
mkdir out
javac -d out src\main\java\com\vit\shams\**\*.java
java -cp out com.vit.shams.Main
```

------------------------------------------------------------------------

## Support

If issues occur: - Check Java version\
- Use batch/shell scripts\
- Ensure correct directory structure
