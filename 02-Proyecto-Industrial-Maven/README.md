# Espacio de Trabajo: Proyecto Industrial (Maven) ⚙️

Bienvenido al entorno de producción del **Corporate Talent Hub**. Este es el espacio donde construirás la solución definitiva aplicando los estándares de la arquitectura de software profesional en Java.

## 🚀 Paso 1: Inicialización con Maven Archetypes
No crearemos las carpetas manualmente. Utilizaremos una plantilla industrial (Archetype). Ejecuta el siguiente comando en tu terminal para generar la estructura estandarizada:

```bash
mvn archetype:generate \
  -DgroupId=com.riwi.talent \
  -DartifactId=corporate-talent-hub \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

---

## 📄 Paso 2: El Contrato Industrial (El archivo pom.xml)
El archivo `pom.xml` (Project Object Model) es el "cerebro" de tu proyecto. Aquí defines las reglas, versiones y librerías. Al abrirlo, asegúrate de configurar estos bloques fundamentales:

### 1. Coordenadas del Proyecto (GAV)
Son el DNI de tu software en el ecosistema mundial:
- **groupId**: El "apellido" de tu organización (ej: `com.riwi.talent`).
- **artifactId**: El nombre del proyecto (ej: `corporate-talent-hub`).
- **version**: El estado de desarrollo (ej: `1.0-SNAPSHOT`).

### 2. Definición del Motor (Properties)
Aquí es donde le dices a Maven que quieres usar **Java 21 LTS** para evitar errores de compatibilidad:
```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

### 3. La Alacena del Proyecto (Dependencies) - *Aviso Importante*
Verás un bloque llamado `<dependencies>`. Por ahora, **no toques nada aquí**. Maven incluye algunas por defecto para pruebas, pero nosotros solo aprenderemos a gestionar este bloque en la **Semana 5**, cuando necesitemos conectar nuestra aplicación con una base de datos externa. 

Recuerda: Un ingeniero avanza paso a paso. Hoy nuestro foco es la **Estructura** y la **Versión**.

---

## 🛠️ Comandos de Supervivencia Maven
Familiarízate con el ciclo de vida de tu proyecto:
- **`mvn clean`**: Limpia la carpeta `target/` (los binarios viejos).
- **`mvn compile`**: Transforma tus archivos `.java` en `.class` organizados.
- **`mvn package`**: Crea el archivo `.jar` listo para ser entregado a un cliente.
- **`mvn test`**: Ejecuta las pruebas unitarias automáticas (Semana 4).

---

## 📚 Documentación de Referencia
Para ser un Coder Avanzado, debes consultar las fuentes oficiales:
1.  **[Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)**: Guía oficial de Maven sobre la estructura del archivo central.
2.  **[Maven Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)**: Entiende qué pasa realmente cuando ejecutas cada comando.
3.  **[Maven Central Repository](https://search.maven.org/)**: El buscador mundial de librerías para Java.
4.  **[Oracle Java Language Specification](https://docs.oracle.com/javase/specs/)**: La biblia técnica de las reglas de Java.

---

## 🔍 Reto de Investigación
1.  **Investigación del GAV**: ¿Por qué en Java el `groupId` se escribe siguiendo el dominio inverso de la empresa (ej: `com.google...`)?
2.  **Convención sobre Configuración**: ¿Qué pasa si pones tu código Java fuera de la carpeta `src/main/java`? ¿Maven lo encontrará?
3.  **Gemini CLI**: `gemini "Explícame qué es un Maven Archetype y por qué facilita el inicio de proyectos corporativos"`

---
¡Adelante, Coder! Ahora tienes las herramientas de un profesional.
