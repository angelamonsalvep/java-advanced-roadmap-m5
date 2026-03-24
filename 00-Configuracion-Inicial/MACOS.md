# Configuración de Entorno en macOS 🍎

En macOS, la gestión de Java se apoya en el comando nativo `/usr/libexec/java_home` y el gestor de paquetes **Homebrew**.

## 🛠️ Paso 1: Instalación con Homebrew (El estándar)
Instala las versiones base de la ruta:
```bash
brew install --cask temurin@8
brew install --cask temurin@11
brew install --cask temurin@17
brew install --cask temurin@21
```

## 🛠️ Paso 2: Configuración Dinámica de Versiones
Añade estos alias a tu archivo de configuración de terminal `.zshrc`: `nano ~/.zshrc`
```bash
# Cambiador dinámico de Java para Mac
alias java8='export JAVA_HOME=$(/usr/libexec/java_home -v 1.8); java -version'
alias java11='export JAVA_HOME=$(/usr/libexec/java_home -v 11); java -version'
alias java17='export JAVA_HOME=$(/usr/libexec/java_home -v 17); java -version'
alias java21='export JAVA_HOME=$(/usr/libexec/java_home -v 21); java -version'

# Versión por defecto al iniciar la sesión
java21
```
Guarda y recarga con: `source ~/.zshrc`.

## 🛠️ Paso 3: Arquitectura Apple Silicon vs Intel
- **M1/M2/M3 (Apple Silicon)**: Asegúrate de que tus JDKs sean `aarch64`.
- **Intel**: Deben ser `x64`.

---

## 🔍 Temas de Investigación para macOS
1.  **jEnv**: Investiga qué es `jEnv` y por qué es la herramienta predilecta en Mac para manejar múltiples versiones de Java.
2.  **Homebrew Cask**: ¿Qué diferencia existe entre `brew install` y `brew install --cask`?
3.  **Rosetta 2**: ¿Qué ocurre si ejecutas un JDK de Intel en un Mac con Apple Silicon?
4.  **Gemini CLI**: Pregúntame: `gemini "Dime cómo hacer que mi Mac use Java 11 para un proyecto específico automáticamente"`
