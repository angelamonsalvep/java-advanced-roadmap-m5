# 🚀 Ejercicio Práctico: Gestión Dinámica de Nómina con Maven (Día 1)

## 🎯 Objetivo
Migrar el sistema de gestión de empleados de arreglos fijos a una estructura dinámica utilizando **ArrayList**, asegurando la integridad de los datos con **Genéricos** y la persistencia segura mediante **Try-with-resources**, todo dentro de un entorno **Maven**.

---

## 🛠️ El Reto: "Nómina Maven 2.0"

Tu tarea es crear un proyecto Maven que gestione una nómina dinámica:
1.  **Estructura Maven**: El código debe residir en `src/main/java`.
2.  **ArrayList & Genéricos**: Gestionar la lista de objetos `Empleado`.
3.  **Try-with-resources**: Exportar la nómina a `nomina_empleados.txt` garantizando el cierre de flujos.

---

## 📝 Instrucciones Paso a Paso

1.  **Configura el `pom.xml`**: Asegúrate de tener el `exec-maven-plugin` para correr el programa fácilmente.
2.  **Crea el Paquete**: Usa el paquete `com.semana3.practica`.
3.  **Implementa la Lógica**:
    -   Usa `List<Empleado> nomina = new ArrayList<>();`.
    -   Agrega y elimina empleados observando el cambio con `.size()`.
4.  **Usa Try-with-resources**: Implementa la escritura de archivos dentro del bloque `try (...)`.

---

## 💻 Configuración Maven (`pom.xml`)

```xml
<project>
    <!-- ... configuración básica ... -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <mainClass>com.semana3.practica.Main</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## 🛠️ Comandos para los Coders

Para compilar y ejecutar el ejercicio desde la terminal:

```bash
# Compilar el proyecto
mvn compile

# Ejecutar la aplicación
mvn exec:java
```

---

## 🔍 Temas para Debatir
1.  **Maven vs Compilación Manual**: ¿Por qué es mejor usar Maven para gestionar un proyecto que crece?
2.  **Ciclo de Vida**: ¿Qué hace `mvn compile` vs `mvn package`?
3.  **Try-with-resources en Maven**: ¿Cambia algo el manejo de excepciones al usar un gestor de dependencias?
