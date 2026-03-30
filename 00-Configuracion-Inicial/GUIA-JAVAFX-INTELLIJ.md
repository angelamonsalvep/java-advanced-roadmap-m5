# Guía Completa: JavaFX con IntelliJ IDEA 🎨🚀

JavaFX es la plataforma estándar de la industria para construir aplicaciones de escritorio modernas, ricas y multiplataforma en Java. Esta guía te llevará desde la configuración inicial hasta la arquitectura de un proyecto profesional.

---

## 🛠️ 1. Requisitos Previos Obligatorios

Antes de empezar, asegúrate de tener instalado:
1.  **JDK 21**: (Ver [Guía de Instalación JDK](./02-Guia-de-Instalacion-JDK.md)).
2.  **IntelliJ IDEA** (Community o Ultimate).
3.  **Scene Builder**: Herramienta visual esencial para diseñar interfaces.
    *   [Descargar Scene Builder de Gluon](https://gluonhq.com/products/scene-builder/)

### 🐧 Especificaciones para Linux (Ubuntu/Debian)
En Linux, JavaFX requiere librerías adicionales del sistema para el renderizado GTK:
```bash
# Actualiza los repositorios
sudo apt update

# Instala las librerías de soporte gráfico y OpenJFX
sudo apt install openjfx libopenjfx-java
```
*Si usas **WSL2**, recuerda que necesitas un servidor X (como GWSL) o usar una versión de Windows 11 que soporte aplicaciones GUI de Linux (WSLg).*

---

## 🚀 2. Creación del Proyecto en IntelliJ

### Opción A: Usando el Wizard (Recomendado)
1.  Abre IntelliJ y selecciona **New Project**.
2.  En el menú lateral, elige **JavaFX**.
3.  **Configuración**:
    *   **Name**: `MiProyectoJavaFX`
    *   **Language**: Java
    *   **Build System**: Maven
    *   **Group**: `com.tuempresa`
    *   **Artifact**: `sistema-ventas`
4.  En la siguiente pantalla, asegúrate de seleccionar **JavaFX Controls** y **JavaFX FXML**.

---

## 🏗️ 3. Entendiendo la Estructura Modular

JavaFX moderno utiliza el sistema de módulos de Java (Project Jigsaw). El archivo clave es `src/main/java/module-info.java`:

```java
module com.ejemplo.proyecto {
    requires javafx.controls;
    requires javafx.fxml;

    // Permite que JavaFX acceda a tus controladores por reflexión
    opens com.ejemplo.controllers to javafx.fxml;
    
    // Exporta el paquete principal para que el SDK pueda ejecutar la app
    exports com.ejemplo;
}
```

---

## 🎨 4. Integración con Scene Builder

Para diseñar visualmente tu interfaz, primero debes instalarlo y luego vincularlo a IntelliJ:

### Instalación
*   **Windows**: Ejecuta el `.exe` y se instalará en `C:\Users\tu_usuario\AppData\Local\SceneBuilder\SceneBuilder.exe`.
*   **Linux (Ubuntu)**: Descarga el `.deb` y ejecuta `sudo dpkg -i SceneBuilder-XX.deb`. La ruta suele ser `/opt/scenebuilder/bin/SceneBuilder`.

### Configuración en IntelliJ
1.  En IntelliJ, ve a `Settings` (Ctrl+Alt+S) o `File > Settings`.
2.  Navega a `Languages & Frameworks > JavaFX`.
3.  En **Path to Scene Builder**, pega la ruta según tu sistema:
    *   **Linux**: `/opt/scenebuilder/bin/SceneBuilder`
    *   **Windows**: La ruta donde se instaló el `.exe`.
4.  **Para usarlo**: Haz clic derecho sobre cualquier archivo `.fxml` en tu proyecto y selecciona **Open in Scene Builder**.

---

## 🧩 5. Arquitectura MVC (Model-View-Controller)

Una aplicación profesional de JavaFX se divide en tres partes:

1.  **Vista (View)**: Archivos `.fxml` (XML que define los botones, tablas, etc.) y `.css` para el estilo.
2.  **Controlador (Controller)**: Clases Java que gestionan los eventos (clics de botones, lógica de entrada).
3.  **Modelo (Model)**: Tus clases de datos (clases POJO).

### Ejemplo de Controlador:
```java
public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("¡Bienvenido a JavaFX!");
    }
}
```

---

## 🔧 6. Configuración de Maven (pom.xml)

Asegúrate de tener las dependencias correctas para evitar errores de compilación:

```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21.0.6</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21.0.6</version>
    </dependency>
</dependencies>
```

---

## ⚠️ 7. Solución de Problemas Comunes

### Error: "JavaFX runtime components are missing"
Esto sucede si intentas ejecutar la clase `Main` directamente sin el plugin de Maven.
*   **Solución**: Usa la pestaña de Maven a la derecha: `Plugins > javafx > javafx:run`.

### Error: "Graphics Device initialization failed"
Suele ocurrir en entornos Linux/WSL o servidores sin entorno gráfico.
*   **Solución**: Asegúrate de tener instalados los drivers de video correctos o usa un entorno de escritorio real.

### Scene Builder no abre mi archivo
Verifica que el `fx:controller` definido en el FXML coincida exactamente con la ruta de tu clase Java.

---

## 🔍 Investigación Técnica
1.  **FXML vs Java Code**: ¿Por qué es mejor diseñar la interfaz en FXML en lugar de crear los botones directamente con código Java?
2.  **CSS en JavaFX**: Investiga cómo aplicar un "Dark Mode" a una aplicación JavaFX usando hojas de estilo `.css`.
3.  **Propiedades y Bindings**: ¿Qué es el concepto de "Binding" en JavaFX y cómo ayuda a mantener la UI actualizada automáticamente?
4.  **Gemini CLI**: `gemini "Dame un ejemplo de cómo crear una tabla dinámica (TableView) en JavaFX con un controlador"`
