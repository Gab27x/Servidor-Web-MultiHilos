#!/bin/bash

# Clean compilation
echo "Cleaning compilation..."
rm -rf bin 

# Compile
echo "Compiling..."
javac -d bin src/main/*.java

# Verify compilation

if [ $? -eq 0 ]; then
    echo "Compilación exitosa. Ejecutando el servidor..."
    java -cp bin main.Server  # Si usas 'package main;', cambia a 'java -cp bin main.Server'
else
    echo "Error en la compilación."
fi