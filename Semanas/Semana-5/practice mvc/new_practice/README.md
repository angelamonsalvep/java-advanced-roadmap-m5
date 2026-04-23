# Aplicación de Gestión de Usuarios - Arquitectura MVC y Patrón DAO

Esta es una aplicación de escritorio desarrollada en Java enfocada en aplicar buenas prácticas de ingeniería de software, como la arquitectura **MVC (Modelo-Vista-Controlador)** y el patrón de diseño **DAO (Data Access Object)**.

La aplicación permite gestionar usuarios (operaciones CRUD: Crear, Leer, Actualizar, Eliminar) interactuando con una base de datos relacional. Su característica principal es su **alta flexibilidad e inyección de dependencias**, lo que permite cambiar tanto la base de datos subyacente como la interfaz gráfica (consola o ventanas Swing) modificando únicamente archivos de configuración, sin necesidad de alterar el código fuente.

---

## 🎯 Objetivo y Reto para el Usuario

El objetivo principal al clonar y utilizar esta aplicación es lograr ejecutarla en tu entorno local. Para ello, deberás:
1. **Configurar la Base de Datos:** Revisar y ajustar el archivo `database.properties` ubicado en `src/main/resources/`. Por defecto, contiene configuraciones de prueba para PostgreSQL y MySQL. Deberás levantar una instancia de ambas bases de datos (local o en la nube), crear la base de datos `appdb` y ajustar las credenciales (url, usuario, contraseña) en dicho archivo. Ten en cuenta que el valor de la propiedad `db.driver` está directamente relacionado con las dependencias en el archivo `pom.xml` (actualmente solo la dependencia de PostgreSQL se encuentra configurada por defecto, por lo que deberás investigar y agregar la dependencia de MySQL para poder usarla).
2. **Ejecutar el Esquema:** El desarrollador debe ejecutar manualmente los scripts DDL del archivo `src/main/resources/schema.sql` en el cliente de base de datos correspondiente para cada una de las instancias (tanto en PostgreSQL como en MySQL). El reto es probar que la aplicación funcione correctamente primero con una base de datos y luego, cambiando la configuración en `database.properties`, probar que funcione con la otra.
3. **Experimentar con las Vistas:** Modificar el archivo `src/main/resources/app.properties` para alternar el valor de `view.type` entre `console` (para usar la terminal) y `swing` (para usar ventanas gráficas con JOptionPane) y observar cómo el sistema se adapta automáticamente.
4. **🌟 Reto Adicional:** Agregar una nueva entidad al sistema (por ejemplo, `Producto`, `Categoria` o `Rol`). Esta nueva entidad debe:
   - Implementar todos los métodos de CRUD básicos heredados de la estructura genérica.
   - Incluir al menos **2 métodos únicos** (por ejemplo, buscar por un atributo específico, filtrar por fechas, etc.) en su propia interfaz y clase DAO.
   - Estar completamente funcional e integrada en el controlador y las vistas, de modo que sus operaciones se puedan realizar tanto desde la vista de `console` como desde la vista `swing`.
   - **Importante:** Debes agregar el script DDL (Data Definition Language) correspondiente para crear la tabla de esta nueva entidad dentro del archivo `schema.sql`, asegurándote de proporcionar la versión tanto para PostgreSQL como para MySQL.

---

## 🏗️ Arquitectura y Componentes Clave

### 1. Patrón DAO (Data Access Object)
La aplicación aísla toda la lógica de acceso a datos utilizando el patrón DAO:
- **Interfaz Genérica `GenericDAO`:** Define las firmas para los métodos CRUD estándar (`findAll`, `findById`, `save`, `update`, `deleteById`).
- **Clase Abstracta `GenericDAOImpl`:** Proporciona la implementación base de casi todos los métodos CRUD para evitar repetir código en cada entidad.
  - *Dato curioso:* ¿Por qué el método `save` no se implementa en la clase genérica y se deja como abstracto? Esto se debe a que la inserción (`INSERT`) a menudo requiere recuperar el ID autogenerado por la base de datos, y el manejo de este ID (y los campos específicos a insertar) varía estrictamente de una entidad a otra, requiriendo una implementación propia en cada clase hija.
- **Interfaz y Clase Específica `UsuarioDAO` / `UsuarioDAOImpl`:** `UsuarioDAO` extiende `GenericDAO` agregando métodos propios de la entidad (ej: `existsByEmail`). `UsuarioDAOImpl` hereda de la clase abstracta (obteniendo los CRUD básicos) e implementa la interfaz propia, resolviendo la lógica específica del modelo `Usuario`. Esto es un excelente ejemplo de **Programación Orientada a Objetos (POO) de alto nivel**.

### 2. Directorio `controller`
Contiene la clase `UsuarioController`.
- Es el "cerebro" o mediador. Se encarga de recibir los eventos e inputs de la Vista, procesarlos, invocar los métodos del `UsuarioDAO` para interactuar con la base de datos, y finalmente devolver la respuesta procesada de vuelta a la Vista.
- Gracias a la **inyección de dependencias**, el controlador no sabe si la vista es una consola o una ventana gráfica; simplemente interactúa con un contrato (la interfaz `View`).

### 3. Directorio `view`
Contiene las interfaces y clases encargadas de la interfaz de usuario:
- **Interfaz `View`:** Define el contrato que cualquier vista debe cumplir (mostrar mensajes, errores, pedir inputs, mostrar menús).
- **`BaseView` / `ConsoleView` / `SwingView`:** Implementaciones concretas de la interfaz. Dependiendo de la configuración en `app.properties`, el sistema inyectará la vista de Consola o la vista Swing (ventanas emergentes) al inicio de la aplicación.

### 4. Patrón de Diseño Singleton
La aplicación hace uso del patrón **Singleton** en componentes críticos que deben tener una única instancia compartida durante todo el ciclo de vida de la ejecución:
- **`AppConfig`:** Se encarga de leer y cargar en memoria las propiedades del archivo `app.properties`. Al ser Singleton, evita leer el archivo repetidas veces, permitiendo un acceso global y optimizado a las configuraciones (como el tipo de vista).
- **`ConnectionManager` (en `db`):** Se encarga de leer `database.properties` y establecer la conexión con la base de datos mediante JDBC. Usa Singleton para garantizar que solo exista un pool o administrador de conexiones activo, optimizando recursos y evitando sobrecargar el motor de base de datos.

---

## 🚀 Guía de Instalación y Ejecución

### Prerrequisitos
- Java JDK 17 o superior.
- Maven instalado en tu sistema.
- Un motor de base de datos (PostgreSQL o MySQL) en ejecución (local o remoto).

### Pasos para iniciar

1. **Clonar el repositorio y abrir en tu IDE.**
2. **Configurar Base de Datos:**
   - Abre tu cliente de base de datos preferido (pgAdmin, DBeaver, terminal).
   - Crea una base de datos llamada `appdb` (o el nombre que desees).
   - Ejecuta el script SQL correspondiente a tu base de datos ubicado en `src/main/resources/schema.sql`.
3. **Modificar `database.properties`:**
   - Ve a `src/main/resources/database.properties`.
   - Descomenta las líneas de tu motor de base de datos (PostgreSQL o MySQL) y comenta las del otro.
   - Ajusta `db.url`, `db.user` y `db.password` con tus credenciales.
4. **(Opcional) Modificar el tipo de vista:**
   - En `src/main/resources/app.properties`, cambia `view.type = console` a `view.type = swing` si deseas usar ventanas gráficas.
5. **Compilar y Ejecutar:**
   - Desde la terminal en la raíz del proyecto, ejecuta:
     ```bash
     mvn clean install
     ```
   - Ejecuta la clase principal `com.app.Main`.

¡Disfruta explorando la arquitectura limpia y modular de esta aplicación!