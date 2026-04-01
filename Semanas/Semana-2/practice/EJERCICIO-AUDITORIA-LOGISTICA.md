# 🚚 Reto Técnico: Auditoría de Carga "Global Logistics"

## 🎯 Objetivo de la Sesión
Desarrollar un sistema que procese datos multidimensionales (matrices) y genere reportes simplificados para el equipo de despacho mediante el uso de Casting Explícito.

---

## 📦 1. Definición de Datos
El sistema debe manejar 3 Sedes de distribución. Cada sede ha registrado el peso de 3 contenedores diferentes.

### Estructura requerida:
*   **Arreglo de Sedes (String[]):** `{"Norte", "Sur", "Centro"}`
*   **Matriz de Pesos (double[][]):**
    *   Fila 0 (Norte): `15.55, 20.30, 10.85`
    *   Fila 1 (Sur): `40.90, 35.15, 50.25`
    *   Fila 2 (Centro): `12.40, 18.60, 14.75`

---

## ⚙️ 2. Requerimientos de Lógica (El "Cómo")
1.  **Recorrido de Matriz:** Implementar bucles `for` anidados. El bucle externo debe iterar sobre las sedes y el interno sobre los contenedores de cada sede.
2.  **Cálculo de Acumulados:** Por cada sede, sumar el peso de sus 3 contenedores para obtener el **Peso Total**.
3.  **Promedio de Carga:** Dividir el peso total entre la cantidad de contenedores (3).
4.  **Transformación de Datos (Casting):** El reporte de despacho **NO** acepta decimales. Debes convertir el promedio (que es `double`) a un tipo de dato `int` usando **Casting Explícito**.

---

## 📊 3. Formato del Reporte Final
La salida en consola debe ser clara y profesional, siguiendo este modelo para cada iteración:

```text
Sede Actual: [Nombre de la Sede]
-------------------------------------------
> Peso Total Acumulado: XX.XX t
> Promedio de Precisión: XX.XXXXX t
> PESO PARA DESPACHO (Cast): XX t
===========================================
```

---

## 🔍 4. Puntos de Análisis para el Equipo
Una vez el código funcione, discutan estos tres puntos clave:

1.  **¿Dónde quedó el resto?:** Observen qué sucede con los decimales al hacer el casting a `int`. ¿Se redondeó hacia arriba o simplemente se eliminaron?
2.  **Manejo de Memoria:** ¿Cómo están conectados los nombres de las sedes con las filas de la matriz?
3.  **Escalabilidad:** ¿Qué pasaría si la Sede "Centro" decide agregar un 4to contenedor? (Concepto de Jagged Arrays).
