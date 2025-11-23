@echo off
echo ============================================
echo SHAMS JUnit Test Execution
echo ============================================

echo Cleaning previous compilation...
if exist out rmdir /s /q out
mkdir out

echo Step 1: Compiling main source code...
javac -d out src\main\java\com\vit\shams\model\*.java ^
src\main\java\com\vit\shams\observer\*.java ^
src\main\java\com\vit\shams\factory\*.java ^
src\main\java\com\vit\shams\service\*.java ^
src\main\java\com\vit\shams\Main.java

if %errorlevel% neq 0 (
    echo ERROR: Failed to compile main source code!
    pause
    exit /b 1
)

echo Step 2: Compiling test files...
javac -d out -cp out;lib\junit-platform-console-standalone-1.9.3.jar ^
src\test\java\com\vit\shams\*.java

if %errorlevel% neq 0 (
    echo ERROR: Failed to compile test files!
    pause
    exit /b 1
)

echo Step 3: Running JUnit Tests...
echo ============================================
java -jar lib\junit-platform-console-standalone-1.9.3.jar ^
--class-path out ^
--scan-class-path ^
--include-classname=".*Test"

echo.
echo ============================================
echo Test execution completed.
pause