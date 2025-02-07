# Servidor Web MultiHilos

Computación en internet 2 - Gabriel Escobar - A00399291

Este proyecto es un servidor HTTP simple con soporte para múltiples hilos.

## 📌 Requisitos

- **Java 17** instalado.
- **Git** instalado.

---

## 🔹 Instrucciones para ejecutar en **Linux**

1. **Clonar el repositorio** (si no lo has hecho):

   ```sh
   git clone https://github.com/Gab27x/Servidor-Web-MultiHilos.git
   cd Servidor-Web-MultiHilos
   ```

2. **Dar permisos de ejecución al script** (solo la primera vez):

   ```sh
   chmod +x run.sh
   ```

3. **Ejecutar el servidor:**

   ```sh
   ./run.sh
   ```

---

## 🔹 Instrucciones para ejecutar en **Windows**

1. **Abrir una terminal (CMD o PowerShell).**
2. **Ir al directorio del proyecto:**
   ```sh
   cd C:\ruta\al\repositorio\Servidor-Web-MultiHilos
   ```
3. **Ejecutar los comandos manualmente:**
   ```sh
   rmdir /s /q bin   # Elimina la carpeta bin (si existe)
   javac -d bin src/main/*.java   # Compila los archivos Java
   java -cp bin main.Server       # Ejecuta el servidor
   ```

---

## 📢 Notas

- Asegúrate de tener `java` y `javac` en el **PATH**.
- Si usas `cmd` en Windows, usa `rmdir` en lugar de `rm -rf` para borrar la carpeta `bin`.
- Si ves el error `Could not find or load main class`, revisa que el paquete en `Server.java` sea `package main;` y que se ejecute con `java -cp bin main.Server`.

---

¡Listo! Ahora el servidor HTTP estará corriendo y podrás probarlo en tu navegador. 🚀

