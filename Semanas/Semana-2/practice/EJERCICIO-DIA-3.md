# 🛰️ DronHunter: Operación Logística 2026
## Día 3: Procesamiento Multidimensional y Casting

---

### 🌐 Storytelling: El Futuro de la Entrega Autónoma
Año 2026. La empresa **Global Logistics** ha revolucionado la cadena de suministro con el despliegue de drones de alta precisión en sus almacenes inteligentes. Como Ingeniero de Software Senior, se te ha encomendado la misión de programar el firmware del **DronHunter-V1**, una unidad autónoma capaz de navegar en una cuadrícula de almacenamiento, identificar paquetes por su peso y gestionar su propia energía.

El almacén es un entorno controlado representado por una **matriz de 4x4**. Tu código deberá garantizar que el dron recoja la información necesaria sin agotar su batería o colisionar con las estanterías del perímetro.

---

### 🎯 Objetivos de Aprendizaje
Al finalizar este reto, habrás dominado:
1.  **Matrices (double[][]):** Gestión de datos en estructuras bidimensionales.
2.  **Casting Explícito (Narrowing):** Conversión de tipos primitivos con pérdida de precisión controlada.
3.  **Records (Java 17+):** Modelado de datos inmutables para coordenadas.
4.  **Switch Expressions:** Implementación de lógica de navegación moderna y concisa.
5.  **Clean Code:** Uso de `var` para inferencia de tipos y *Text Blocks* para interfaces de usuario limpias.

---

### 🛠️ Especificaciones Técnicas

#### 1. El Mapa del Almacén
Utiliza una matriz `double[][] warehouse` de 4x4. 
- Un valor de `0.0` indica que el espacio está vacío.
- Cualquier valor superior a `0.0` representa el peso de un paquete en kilogramos (ej: `5.75`, `12.3`, `2.5`).

#### 2. Modelado de Posición
Define un **Record** llamado `Coordenada(int fila, int columna)`. Este será el encargado de representar la ubicación actual del dron de forma inmutable.

#### 3. Motor de Navegación
Implementa un sistema de comandos ("w", "a", "s", "d") utilizando una **Switch Expression**. Cada comando debe retornar una **nueva instancia** del Record `Coordenada`.
- `w`: Arriba (fila - 1)
- `s`: Abajo (fila + 1)
- `a`: Izquierda (columna - 1)
- `d`: Derecha (columna + 1)

#### 4. El Corazón del Día: Casting de Energía
El dron cuenta con una batería inicial del **100%**. Al ejecutar el comando `scan` sobre un paquete:
- El sensor de peso devuelve un `double`.
- Sin embargo, el subsistema de energía es analógico y **solo acepta valores enteros** para procesar el consumo. 
- Debes realizar un **Casting Explícito (Narrowing)** del peso del paquete (`double`) a un costo de batería (`int`). 
- *Ejemplo:* Si el paquete pesa `8.9 kg`, la batería se reduce en `8` unidades.

#### 5. Visualización Dinámica
Cada vez que el dron realice un movimiento o acción, se debe imprimir el estado del almacén utilizando bucles `for` anidados.

---

### 🎮 Guía de Símbolos
*   `[ D ]`: Posición actual del DronHunter.
*   `[ 📦 ]`: Espacio con un paquete detectado (peso > 0.0).
*   `[ . ]`: Espacio vacío (0.0).

---

### 🏁 Condiciones de Victoria y Derrota

*   **Victoria:** Haber escaneado todos los paquetes del almacén (limpiar el inventario).
*   **Derrota (System Failure):**
    *   **Low Battery:** Si la batería llega a 0 o menos después de un escaneo.
    *   **Collision:** Intentar moverse fuera de los límites de la matriz (debes capturar o prevenir la `ArrayIndexOutOfBoundsException`).

---

### 📝 Ejemplo de Interfaz (Text Blocks)
```text
==========================================
      DRONHUNTER: INTERFACE V.2026        
==========================================
BATERÍA: 85% | POSICIÓN: (2, 1)
------------------------------------------
[ . ] [ 📦 ] [ . ] [ . ]
[ . ] [ D ] [ . ] [ 📦 ]
[ 📦 ] [ . ] [ . ] [ . ]
[ . ] [ . ] [ 📦 ] [ . ]
------------------------------------------
Controles: [W/A/S/D] Mover | [E] Scan | [Q] Salir
```

---

### 🤔 Preguntas de Reflexión (Post-Misión)

1.  **Pérdida de Datos:** ¿Qué pasó con los gramos (decimales) de los paquetes al hacer el casting a `int`? ¿Es esta una práctica segura en un entorno real de pesaje?
2.  **Robustez:** ¿Cómo se comportó la memoria o el flujo del programa al intentar salir de la matriz? ¿Qué técnica utilizaste para evitar que el programa "explotara"?
3.  **Inmutabilidad:** Al usar un Record para la `Coordenada`, ¿por qué no pudimos simplemente hacer `posicion.fila++`? ¿Qué ventaja aporta esto a la integridad de los datos de navegación?

---
*Senior Engineering Note: "En el código de alto nivel, la precisión es una elección, pero la seguridad es un requisito. ¡Buen vuelo, Coder!"*
