# Guía de Visual Studio Code (VSC) 💙

VSC no es un IDE por defecto, es un editor de texto ultra-rápido que se convierte en un IDE de Java mediante extensiones. Es el preferido para microservicios y desarrollo en la nube.

## 🛠️ Instalación por Sistema Operativo
Descarga VSC desde: [Visual Studio Code](https://code.visualstudio.com/).

*   **Windows**: Instalador `.exe`.
*   **Linux**: `sudo snap install code --classic` o vía `.deb` / `.rpm`.
*   **macOS**: Descarga el `.zip`, extráelo y arrastra **Code** a Aplicaciones.

## ⚙️ Configuración Inicial Obligatoria
1.  **Extension Pack for Java**: Ve al icono de extensiones (Ctrl+Shift+X) e instala el **"Extension Pack for Java"** de Microsoft. Esto instala soporte de Red Hat para Maven y Debugging.
2.  **JDK Configuration**: Ve a `File > Preferences > Settings` y busca `java.jdt.ls.java.home`. Pon la ruta de tu **JDK 21**.
3.  **Clean Language Server**: A veces VSC se "traba" con Java. Usa el comando: `F1 > Java: Clean Java Language Server Workspace`.

## 🚀 Características Industriales
- **Ligereza**: Se abre mucho más rápido que IntelliJ o Eclipse.
- **Políglota**: Puedes trabajar en un mismo proyecto con Java, SQL, Docker y React sin cambiar de herramienta.
- **Remote Development**: Permite programar dentro de un contenedor Docker o en un servidor remoto mediante SSH.

---

## 🔍 Investigación Técnica - VSC
1.  **Red Hat en VSC**: Investiga por qué Red Hat es quien desarrolla la extensión de Java para el editor de Microsoft. ¿Qué es el "Language Server Protocol"?
2.  **Liviandad vs Funcionalidad**: ¿Por qué un proyecto con 10,000 clases podría funcionar más lento en VSC que en IntelliJ?
3.  **Cloud-Native**: ¿Qué significa el término "Cloud-Native" y por qué VSC es tan popular entre los desarrolladores que trabajan con Kubernetes y Docker?
4.  **Gemini CLI**: `gemini "Dame un ejemplo de cómo configurar el archivo settings.json de VSC para reconocer múltiples versiones de Java"`
