# Configuración de Entorno en Windows 🪟

La gestión de múltiples versiones de Java en Windows depende directamente de las **Variables de Entorno**. El objetivo es poder cambiar de versión a voluntad sin que la terminal falle.

## 🛠️ Paso 1: Instalación de las 4 Versiones (JDK)
Descarga la distribución **Adoptium (Temurin)**:
1.  [Java 8 MSI](https://adoptium.net/temurin/releases/?version=8)
2.  [Java 11 MSI](https://adoptium.net/temurin/releases/?version=11)
3.  [Java 17 MSI](https://adoptium.net/temurin/releases/?version=17)
4.  [Java 21 MSI](https://adoptium.net/temurin/releases/?version=21)

## 🛠️ Paso 2: Gestión de Variables (El "Cerebro")
1.  En el Menú Inicio, busca: **"Editar las variables de entorno del sistema"**.
2.  Dentro de **Variables del sistema**, crea estas entradas con sus rutas exactas:
    - `JAVA8_HOME`: `C:\Program Files\Eclipse Adoptium\jdk8.x.x`
    - `JAVA11_HOME`: `C:\Program Files\Eclipse Adoptium\jdk11.x.x`
    - `JAVA17_HOME`: `C:\Program Files\Eclipse Adoptium\jdk17.x.x`
    - `JAVA21_HOME`: `C:\Program Files\Eclipse Adoptium\jdk21.x.x`
3.  Crea la variable `JAVA_HOME` y asígnale el valor de la versión por defecto (ej: `%JAVA21_HOME%`).
4.  Edita la variable `Path`, añade al principio: `%JAVA_HOME%\bin`.

## 🛠️ Paso 3: Verificación
Abre PowerShell o CMD y verifica:
```cmd
java -version
```

---

## 🔍 Temas de Investigación para Windows
1.  **Chocolatey / Winget**: Investiga cómo instalar y actualizar Java 21 con un solo comando de consola.
2.  **Scripts de Cambio**: Crea un archivo `.bat` que cambie temporalmente la variable `JAVA_HOME`.
3.  **Prioridades**: ¿Qué ocurre si instalas un JDK de Oracle después de un JDK de Adoptium? ¿Quién toma prioridad en el registro del sistema?
4.  **Gemini CLI**: `gemini "Dame un script de PowerShell para cambiar el JAVA_HOME de forma rápida"`
