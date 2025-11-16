@echo off
echo SHAMS Healthcare System - Complete Compilation
echo =============================================

echo Cleaning previous compilation...
if exist out rmdir /s /q out
mkdir out

echo Step 1: Compiling User base class...
javac -d out src\main\java\com\vit\shams\model\User.java

echo Step 2: Compiling TimeSlot (needed for Schedule)...
javac -d out src\main\java\com\vit\shams\model\TimeSlot.java

echo Step 3: Compiling Schedule...
javac -d out src\main\java\com\vit\shams\model\Schedule.java

echo Step 4: Compiling MedicalRecord...
javac -d out src\main\java\com\vit\shams\model\MedicalRecord.java

echo Step 5: Compiling Billing...
javac -d out src\main\java\com\vit\shams\model\Billing.java

echo Step 6: Compiling Patient and Doctor...
javac -d out src\main\java\com\vit\shams\model\Patient.java
javac -d out src\main\java\com\vit\shams\model\Doctor.java

echo Step 7: Compiling Appointment...
javac -d out src\main\java\com\vit\shams\model\Appointment.java

echo Step 8: Compiling NotificationObserver interface...
javac -d out src\main\java\com\vit\shams\observer\NotificationObserver.java

echo Step 9: Compiling EmailNotification and SMSNotification...
javac -d out src\main\java\com\vit\shams\observer\EmailNotification.java
javac -d out src\main\java\com\vit\shams\observer\SMSNotification.java

echo Step 10: Compiling NotificationFactory...
javac -d out src\main\java\com\vit\shams\factory\NotificationFactory.java

echo Step 11: Compiling NotificationService...
javac -d out src\main\java\com\vit\shams\service\NotificationService.java

echo Step 12: Compiling AppointmentService...
javac -d out src\main\java\com\vit\shams\service\AppointmentService.java

echo Step 13: Compiling Main class...
javac -d out src\main\java\com\vit\shams\Main.java

echo.
echo =============================================
echo COMPILATION COMPLETE! Running SHAMS System...
echo =============================================
java -cp out com.vit.shams.Main

echo.
pause