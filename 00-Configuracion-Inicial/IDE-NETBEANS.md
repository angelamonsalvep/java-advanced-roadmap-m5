# Guía de Apache NetBeans (The Community Official) 📦

NetBeans es el IDE "oficial" en términos de cercanía con la especificación de Java. Es excelente para el aprendizaje inicial y para el desarrollo de interfaces gráficas tradicionales.

## 🛠️ Instalación por Sistema Operativo
Descarga la última versión desde: [Apache NetBeans Releases](https://netbeans.apache.org/).

*   **Windows**: Ejecuta el instalador `.exe`.
*   **Linux**: Descarga el script `.sh`, dale permisos (`chmod +x`) y ejecútalo.
*   **macOS**: Usa el instalador `.pkg`.

## ⚙️ Configuración Inicial Obligatoria
1.  **JDK Default**: Si NetBeans no encuentra tu Java, edita el archivo `netbeans.conf` (en la carpeta `/etc` de la instalación) y cambia la línea `netbeans_jdkhome` a tu ruta de **JDK 21**.
2.  **Plugins Dinámicos**: Ve a `Tools > Plugins` y asegúrate de activar el soporte para Java si no lo está por defecto.
3.  **Maven out-of-the-box**: NetBeans tiene el mejor soporte nativo para Maven. No necesitas configurar nada; él lee el `pom.xml` directamente.

## 🚀 Características Industriales
- **GUI Builder (Swing)**: Es el mejor IDE para diseñar ventanas y botones mediante "arrastrar y soltar" (Drag and Drop).
- **Simplicidad**: Menos complejo de configurar que Eclipse y más ligero que IntelliJ.
- **Soporte de Jakarta EE**: Muy fuerte en el desarrollo de servidores de aplicaciones tradicionales.

---

## 🔍 Investigación Técnica - NetBeans
1.  **Swing vs JavaFX**: Investiga cómo el diseñador visual de NetBeans facilita el uso de estas dos tecnologías de escritorio.
2.  **Apache Foundation**: Investiga por qué Oracle donó NetBeans a la Fundación Apache y qué significó esto para el futuro del IDE.
3.  **Project Groups**: ¿Cómo usa NetBeans los grupos de proyectos para organizar múltiples microservicios de forma lógica?
4.  **Gemini CLI**: `gemini "Dame un ejemplo de cómo crear un proyecto Maven básico en NetBeans y cómo gestionar las dependencias visualmente"`
