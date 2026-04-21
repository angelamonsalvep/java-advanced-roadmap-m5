# 🎓 Livecoding: Master en JDBC Moderno & Patrón DAO
## Sistema de Gestión de Cursos Profesionales

### 🚀 Objetivo del Ejercicio
Aprenderás a construir una capa de persistencia robusta, segura y escalable. Al finalizar, habrás implementado una solución que utiliza el **Patrón DAO** para separar la lógica, **Try-with-Resources** para evitar fugas de memoria y **PreparedStatement** para blindar la aplicación contra inyección SQL.

---

### 📋 Requisitos y Preparación
1. **Base de Datos:** Tener una instancia de PostgreSQL o MySQL activa.

#### 🐧 Creación de la DB (Comandos Linux)

**PostgreSQL:**
```bash
# Crear la base de datos 'academia' usando el usuario administrativo
sudo -u postgres createdb academia
```

**MySQL:**
```bash
# Crear la base de datos 'academia' (se pedirá contraseña de root)
mysql -u root -p -e "CREATE DATABASE academia;"
```

2. **Estructura de la Tabla:**
Conéctate a tu base de datos (`psql academia` o `mysql academia`) y ejecuta:
```sql
CREATE TABLE cursos (
    id SERIAL PRIMARY KEY, -- 'AUTO_INCREMENT' en MySQL
    nombre VARCHAR(100) NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    duracion_horas INT NOT NULL
);
```

---

### 🛠️ Guía Paso a Paso con Ejemplos

#### 1. Dependencias (Maven)
En tu archivo `pom.xml`, debes añadir el "traductor" (Driver) para tu base de datos.
**¿Por qué?** Sin el driver, Java no sabe cómo comunicarse físicamente con el motor de DB.

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.2</version>
</dependency>
```

#### 2. Utilidad de Conexión (`DatabaseConnection.java`)
Crea una clase que centralice la apertura de conexiones.
**Explicación:** Centralizar la conexión permite cambiar las credenciales o el motor en un solo lugar.

```java
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/academia";
    private static final String USER = "tu_usuario";
    private static final String PASS = "tu_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
```

#### 3. El Modelo de Datos (`Curso.java`)
Crea un POJO (Plain Old Java Object) que represente la tabla.
**¿Por qué?** En Java trabajamos con objetos, no con filas sueltas de texto.

```java
public class Curso {
    private Integer id;
    private String nombre;
    private String instructor;
    private int duracionHoras;

    // Constructores, Getters y Setters
    // Tip: Añade un toString() para facilitar el debugging
}
```

#### 4. El Contrato de Datos (`CursoDAO.java`)
Define una interfaz para tus operaciones.
**Explicación:** Las interfaces nos permiten "desacoplar". La lógica de negocio solo sabe que puede "guardar", no le importa si se guarda en SQL, en un archivo o en una nube.

```java
public interface CursoDAO {
    void guardar(Curso curso);
    List<Curso> listarTodos();
    void eliminar(int id);
}
```

#### 5. Implementación con Seguridad Industrial (`CursoDAOImpl.java`)
Aquí es donde aplicamos el **JDBC Moderno**.

```java
public class CursoDAOImpl implements CursoDAO {

    @Override
    public void guardar(Curso curso) {
        String sql = "INSERT INTO cursos (nombre, instructor, duracion_horas) VALUES (?, ?, ?)";

        // 1. Try-with-resources: Abre y CIERRA automáticamente Connection y PreparedStatement
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 2. PreparedStatement: Los '?' evitan la inyección SQL
            ps.setString(1, curso.getNombre());
            ps.setString(2, curso.getInstructor());
            ps.setInt(3, curso.getDuracionHoras());

            ps.executeUpdate();
            System.out.println("✅ Curso guardado con éxito.");

        } catch (SQLException e) {
            System.err.println("❌ Error al guardar curso: " + e.getMessage());
        }
    }

    @Override
    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { // El ResultSet también se cierra solo

            while (rs.next()) {
                // 3. Mapping: Convertimos la fila de la DB en un objeto Java
                Curso c = new Curso();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setInstructor(rs.getString("instructor"));
                c.setDuracionHoras(rs.getInt("duracion_horas"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cursos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

#### 6. Prueba Final (`App.java`)
Ejecuta el flujo completo.

```java
public class App {
    public static void main(String[] args) {
        CursoDAO dao = new CursoDAOImpl();

        // Crear objeto
        Curso nuevo = new Curso(null, "Java Advanced", "Angela Monsalve", 40);

        // Persistir
        dao.guardar(nuevo);

        // Listar
        dao.listarTodos().forEach(System.out::println);
    }
}
```

---

### 💡 Puntos de Discusión Cruciales para el Mentor
1. **Memory Leaks:** ¿Qué pasaría si abrimos 1000 conexiones y olvidamos el `conn.close()`? (El sistema colapsa).
2. **AutoCloseable:** Explica que el `try-with-resources` solo funciona con objetos que implementan la interfaz `AutoCloseable`.
3. **SQLException:** ¿Por qué es una "Checked Exception"? (Porque los errores de red/DB son externos y DEBEN manejarse).
4. **SQL Injection:** Pide a un coder que intente romper el sistema usando una concatenación de String vs el `?`.
