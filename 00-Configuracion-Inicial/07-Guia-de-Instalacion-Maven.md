# Guía de Instalación y Configuración de Apache Maven

Apache Maven es una herramienta esencial en el ecosistema Java moderno. A diferencia de las compilaciones manuales, Maven estandariza la forma en que los proyectos se construyen, gestionan y distribuyen.

---

## 1. ¿Qué es Apache Maven?

Apache Maven es una herramienta de **gestión de proyectos y automatización de construcción** basada en el concepto de un **POM (Project Object Model)**. Se encarga del ciclo de vida de un proyecto, desde la compilación del código fuente hasta la generación del artefacto final (JAR, WAR, EAR).

### ¿Para qué se usa?
*   **Gestión de Dependencias:** Descarga automáticamente las librerías necesarias y resuelve conflictos de versiones.
*   **Automatización de Construcción:** Ejecuta tareas repetitivas como compilar, probar y empaquetar con comandos simples.
*   **Estandarización:** Define una estructura de carpetas universal (`src/main/java`, `src/test/java`, etc.), facilitando que cualquier desarrollador entienda el proyecto rápidamente.
*   **Integración Continua (CI/CD):** Es el motor detrás de la mayoría de los pipelines de Jenkins, GitHub Actions o GitLab CI.

---

## 2. Requisitos Previos

Antes de instalar Maven, **debes tener instalado el JDK** y configurada la variable de entorno `JAVA_HOME`.
*   Verifica tu instalación con: `java -version`

---

## 3. Guía de Instalación por Sistema Operativo

### 🪟 Windows

1.  **Descarga:** Ve a [maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) y descarga el archivo **Binary zip archive** (ej. `apache-maven-3.x.x-bin.zip`).
2.  **Extracción:** Descomprime el contenido en una carpeta sin espacios, preferiblemente `C:\apache-maven`.
3.  **Variables de Entorno:**
    *   Busca "Editar las variables de entorno del sistema" en el menú Inicio.
    *   Crea una nueva variable de sistema llamada `MAVEN_HOME` con la ruta de la carpeta extraída (ej. `C:\apache-maven`).
    *   Edita la variable `Path` y añade: `%MAVEN_HOME%\bin`.
4.  **Verificación:** Abre un CMD o PowerShell y escribe: `mvn -version`.

### 🐧 Linux (Ubuntu/Debian/Fedora)

**Opción A: Gestor de Paquetes (Recomendado)**
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install maven

# Fedora
sudo dnf install maven
```

**Opción B: Manual (Para versiones específicas)**
1. Descarga el `Binary tar.gz archive`.
2. Extrae en `/opt/`: `sudo tar -xvf apache-maven-*-bin.tar.gz -C /opt/`.
3. Configura el perfil: `sudo nano /etc/profile.d/maven.sh` y añade:
   ```bash
   export MAVEN_HOME=/opt/apache-maven-3.x.x
   export PATH=$MAVEN_HOME/bin:$PATH
   ```
4. Carga los cambios: `source /etc/profile.d/maven.sh`.

### 🍎 macOS

**Opción A: Homebrew (Recomendado)**
Si tienes instalado Homebrew, simplemente ejecuta:
```bash
brew install maven
```

**Opción B: Manual**
1. Descarga el `Binary tar.gz archive`.
2. Extrae en una carpeta (ej. `/usr/local/apache-maven`).
3. Añade al final de tu archivo `~/.zshrc` o `~/.bash_profile`:
   ```bash
   export MAVEN_HOME=/usr/local/apache-maven-3.x.x
   export PATH=$MAVEN_HOME/bin:$PATH
   ```
4. Aplica cambios: `source ~/.zshrc`.

---

## 4. Estructura Estándar de un Proyecto Maven

Maven impone una estructura de carpetas por defecto. Seguirla garantiza que Maven sepa dónde encontrar el código sin configuración adicional:

```text
mi-proyecto/
├── pom.xml                 # Archivo de configuración principal
├── src/
│   ├── main/
│   │   ├── java/           # Código fuente (.java)
│   │   └── resources/      # Archivos de configuración, imágenes, etc.
│   └── test/
│       ├── java/           # Pruebas unitarias (JUnit, TestNG)
│       └── resources/      # Recursos para pruebas
└── target/                 # Carpeta generada (se crea al compilar)
```

---

## 5. El archivo `pom.xml` (Project Object Model)

Es el "corazón" de un proyecto Maven. Es un archivo XML que contiene la configuración del proyecto, sus dependencias y las instrucciones de construcción.

### Elementos clave del POM:

*   **`groupId`**: Es el identificador único de tu organización o grupo. Sigue la convención de nombres de paquetes de Java (ej. `com.riwi.talent`).
*   **`artifactId`**: El nombre del proyecto o módulo (ej. `sistema-nominas`).
*   **`version`**: La versión actual del proyecto. Se suele usar el sufijo `-SNAPSHOT` para versiones en desarrollo.
*   **`packaging`**: Define el formato de salida del proyecto (ver sección 6).
*   **`properties`**: Secciones para definir variables, como la versión de Java o la codificación de caracteres, para evitar repetirlas.
*   **`dependencies`**: El listado de librerías externas que tu proyecto necesita para funcionar.
*   **`build`**: Configuración de cómo se debe construir el proyecto, incluyendo plugins de compilación o ejecución.

### Ejemplo de un `pom.xml` completo:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Coordenadas del Proyecto -->
    <groupId>com.riwi.talent</groupId>
    <artifactId>sistema-nominas</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- Ejemplo: Librería para manejar JSON -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
    </dependencies>
</project>
```

---

## 6. Tipos de Empaquetado (`<packaging>`)

Maven permite generar diferentes tipos de archivos según el destino de la aplicación. Si no se especifica, el valor por defecto es **`jar`**.

### 1. `jar` (Java ARchive)
*   **Definición:** El estándar de Java. Contiene clases compiladas y recursos.
*   **Uso:** Aplicaciones de consola, librerías compartidas o microservicios (Spring Boot).
*   **Caso de uso:** Crear una herramienta de escritorio o una librería de utilidades que otros proyectos importarán.

### 2. `war` (Web ARchive)
*   **Definición:** Contiene la aplicación web completa, incluyendo servlets, JSP, archivos HTML, CSS, JS y librerías en la carpeta `WEB-INF/lib`.
*   **Uso:** Aplicaciones web tradicionales que se despliegan en un servidor de aplicaciones o contenedor de servlets.
*   **Caso de uso:** Un sistema de gestión escolar que se subirá a un servidor **Apache Tomcat** o **WildFly**.

### 3. `ear` (Enterprise ARchive)
*   **Definición:** Un contenedor de alto nivel que puede incluir múltiples módulos `WAR` y archivos `JAR` (como EJBs).
*   **Uso:** Aplicaciones empresariales complejas distribuidas en servidores de aplicaciones JEE.
*   **Caso de uso:** Un sistema bancario masivo que separa la lógica de negocio (EJB) de la interfaz de usuario web (WAR).

### 4. `pom`
*   **Definición:** No genera un archivo binario. Solo sirve como un "proyecto padre" o contenedor.
*   **Uso:** Proyectos multimódulo donde un `pom.xml` central gestiona las versiones y dependencias de varios subproyectos.
*   **Caso de uso:** Una suite de software con un módulo `core`, un módulo `api` y un módulo `web`, todos compartiendo la misma configuración.

---

## 7. Ciclo de Vida y Comandos Comunes

Maven funciona a través de "phases" (fases). Cuando ejecutas una fase, Maven ejecuta todas las anteriores automáticamente.

### Comandos esenciales:

1.  **`mvn clean`**: Borra la carpeta `target/`. Es útil para limpiar compilaciones previas y evitar errores residuales.
2.  **`mvn compile`**: Compila el código fuente en `src/main/java` y genera los archivos `.class` en `target/classes`.
3.  **`mvn test`**: Ejecuta las pruebas unitarias que se encuentran en `src/test/java`.
4.  **`mvn package`**: Toma el código compilado y lo empaqueta en su formato final (generalmente un archivo **.jar**).
5.  **`mvn install`**: Empaqueta el proyecto y lo guarda en tu repositorio local (`~/.m2/repository`), permitiendo que otros proyectos locales lo usen como dependencia.

> **Ejemplo de flujo común:** `mvn clean package` (Limpia y genera un nuevo JAR).

---

## 7. ¿Cómo agregar una dependencia?

Si necesitas una librería (ej. para conectar a MySQL o usar JSON), no descargas el JAR manualmente. Vas a [MVN Repository](https://mvnrepository.com/), buscas la librería y copias el bloque `<dependency>` dentro de la sección `<dependencies>` de tu `pom.xml`.

**Ejemplo para MySQL:**
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
</dependency>
```

---

## 8. Verificación de Instalación

En cualquier sistema operativo, al ejecutar:
```bash
mvn -version
```
Deberías ver una salida similar a esta:
`Apache Maven 3.x.x (non-versioned-hash; 20xx-xx-xxTxx:xx:xxZ)`
`Maven home: /path/to/maven`
`Java version: 17.x.x, vendor: Oracle Corporation...`
