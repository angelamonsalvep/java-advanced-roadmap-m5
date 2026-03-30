# Reto: El Configurador de Estaciones de Trabajo (Workstation Setup) 💻

Este proyecto forma parte de la práctica de **Java SE** enfocado en el uso de estructuras de control, específicamente el uso de `switch` anidados.

## 📝 Descripción del Reto

Eres el encargado de compras de una empresa tecnológica. Tu programa debe permitir al usuario configurar su equipo de trabajo eligiendo primero el departamento y luego el perfil de rendimiento.

### Instrucciones

1.  **Nivel 1 (Switch Principal):** El usuario debe elegir un Departamento:
    *   `'D'` (Desarrollo)
    *   `'M'` (Marketing)
2.  **Nivel 2 (Switch Anidado):** Una vez elegido el departamento, se debe pedir el Nivel de Rendimiento:
    *   `1` (Estándar)
    *   `2` (Premium)

### 🖥️ Lógica de Salida

Se debe mostrar el equipo asignado según la combinación:

| Departamento | Rendimiento | Equipo Asignado |
| :--- | :--- | :--- |
| Desarrollo (D) | 1 (Estándar) | Laptop i7, 16GB RAM, Monitor 24" |
| Desarrollo (D) | 2 (Premium) | MacBook Pro M3, 32GB RAM, Monitor 32" 4K |
| Marketing (M) | 1 (Estándar) | Laptop i5, 8GB RAM, Tablet básica |
| Marketing (M) | 2 (Premium) | iMac 24", 16GB RAM, Tableta Digitalizadora Pro |

### 🚨 Reglas de Validación

*   Si el departamento no es 'D' ni 'M', debe mostrar: `"Departamento no reconocido"`.
*   Si el nivel de rendimiento no es 1 ni 2, debe mostrar: `"Nivel de rendimiento inválido"`.
*   **Bonus:** El código debe aceptar tanto mayúsculas como minúsculas (`'D'`/`'d'`, `'M'`/`'m'`).

---

## 🛠️ Tecnologías

*   **Java SE**
*   **Maven** (Gestor de dependencias)

## 🚀 Estructura del Proyecto

*   `src/main/java/com/riwi/App.java`: Clase principal donde se implementará la lógica.
*   `pom.xml`: Configuración del proyecto Maven.

---

## 💡 Ejemplo de Estructura Visual (Pista)

```java
switch (departamento) {
    case 'D', 'd':
        switch (nivel) {
            case 1: // ... logic
            case 2: // ... logic
            default: // ... invalid level
        }
        break;
    case 'M', 'm':
        // Repetir switch para rendimiento de Marketing
        break;
    default:
        // Departamento no válido
}
```
