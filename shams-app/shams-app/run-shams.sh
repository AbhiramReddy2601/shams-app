#!/bin/bash

echo "Compiling SHAMS Application..."
javac -d out src/main/java/com/vit/shams/**/*.java src/main/java/com/vit/shams/Main.java

if [ $? -eq 0 ]; then
    echo "Compilation successful! Running application..."
    echo "=============================================="
    java -cp out com.vit.shams.Main
else
    echo "Compilation failed! Please check your Java installation."
fi