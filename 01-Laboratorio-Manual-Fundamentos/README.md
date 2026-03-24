# Laboratorio de Fundamentos de Java (Gestión Nativa) 🏛️

Este proyecto está diseñado para comprender las bases del lenguaje Java mediante la gestión manual de sus procesos. El objetivo es que el estudiante comprenda la mecánica interna de compilación y ejecución antes de pasar a herramientas de automatización industrial.

## 🎯 Objetivos de Aprendizaje
1.  Comprender el flujo de trabajo nativo de Java a través de la terminal.
2.  Gestionar la estructura de paquetes y la salida de binarios de forma manual.
3.  Entender la importancia del Classpath en la resolución de dependencias locales.

## 🛠️ Lineamientos de Trabajo
- **Entorno**: Se recomienda el uso exclusivo de la terminal (CMD, PowerShell o Bash) para todas las operaciones de compilación y ejecución.
- **Enfoque**: Se explorará la creación de archivos `.java` y su transformación a Bytecode (`.class`) sin abstracciones de terceros.

---

## 🏗️ Reto del Día 1: Compilación en Entornos Distribuidos
El primer desafío consiste en compilar una clase organizada en un paquete (`package notes;`).

**Comandos de Referencia:**
Para compilar y ejecutar manteniendo el orden de los binarios:
```bash
# Compilar dirigiendo el resultado a la carpeta de binarios
javac -d bin src/notes/Arquitectura.java

# Ejecutar especificando el Classpath y el nombre calificado de la clase
java -cp bin notes.Arquitectura
```

## 🔍 Temas de Investigación
1.  **Organización**: ¿Por qué es una buena práctica separar el código fuente (`src`) de los archivos compilados (`bin` o `target`)?
2.  **Bytecode**: Al abrir un archivo `.class`, ¿qué información logras identificar y por qué no es legible para el ser humano?
3.  **Resolución de Nombres**: ¿Cuál es la función del parámetro `-d` en el comando `javac` con respecto a la creación de carpetas de paquetes?
