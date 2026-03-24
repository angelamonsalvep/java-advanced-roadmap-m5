# Configuración de Entorno en Linux 🐧

En Linux, la clave es dominar el sistema de **alternativas** y las configuraciones de los perfiles de terminal para gestionar múltiples versiones de Java.

## 🛠️ Paso 1: Instalación de JDKs (Debian/Ubuntu)
Ejecuta estos comandos para tener la colección completa:
```bash
sudo apt update
sudo apt install openjdk-8-jdk
sudo apt install openjdk-11-jdk
sudo apt install openjdk-17-jdk
sudo apt install openjdk-21-jdk
```

## 🛠️ Paso 2: El Tablero de Control (update-alternatives)
Linux tiene una herramienta para elegir cuál versión es la "oficial" en cada momento.
1.  **Configurar Java**: `sudo update-alternatives --config java`
2.  **Configurar el Compilador**: `sudo update-alternatives --config javac`
*Verás una lista con las 4 versiones. Elige el número de la versión que desees usar.*

## 🛠️ Paso 3: Configuración de JAVA_HOME Dinámico
Añade este script a tu archivo `.bashrc` o `.zshrc`: `nano ~/.bashrc`
```bash
# Cambiador rápido de Java
alias setjava8='export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64; export PATH=$JAVA_HOME/bin:$PATH; java -version'
alias setjava11='export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64; export PATH=$JAVA_HOME/bin:$PATH; java -version'
alias setjava17='export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64; export PATH=$JAVA_HOME/bin:$PATH; java -version'
alias setjava21='export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64; export PATH=$JAVA_HOME/bin:$PATH; java -version'
```
Guarda y recarga con: `source ~/.bashrc`.

---

## 🔍 Temas de Investigación para Linux
1.  **Directorio JVM**: Busca la ruta `/usr/lib/jvm`. ¿Qué diferencia hay entre las carpetas con `-amd64` y las que no lo tienen?
2.  **Simbólicos**: Investiga qué es un **enlace simbólico (symlink)**. ¿Cómo usa `update-alternatives` estos enlaces para cambiar de versión sin mover archivos?
3.  **SDKMAN!**: Instala `sdkman` y mira cómo simplifica todo esto con un solo comando: `sdk use java 8.0.x-tem`.
4.  **Gemini CLI**: Pregúntame: `gemini "Dime cómo hacer que cada nueva terminal inicie con Java 21 por defecto"`
