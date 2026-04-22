/* ============================================================
   PROYECTO: MVC + DAO + JDBC + Interfaces Genéricas + Multi-Vista
   Estructura: com.app.*
   ============================================================ */


// ─────────────────────────────────────────────────────────────
// 1. CONFIG LAYER
// ─────────────────────────────────────────────────────────────

// ── src/main/resources/database.properties ──
/*
db.url      = jdbc:mysql://localhost:3306/appdb
db.user     = root
db.password = secret
db.driver   = com.mysql.cj.jdbc.Driver
*/

// ── src/main/resources/app.properties ──
/*
app.name    = MVC Demo
app.version = 1.0
view.type   = console
*/

// ── com/app/config/AppConfig.java ──
package com.app.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton que centraliza la configuración de la app.
 * Carga database.properties y app.properties del classpath.
 */
public class AppConfig {

    private static AppConfig instance;
    private final Properties dbProps  = new Properties();
    private final Properties appProps = new Properties();

    private AppConfig() {
        load("database.properties", dbProps);
        load("app.properties",      appProps);
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) instance = new AppConfig();
            }
        }
        return instance;
    }

    private void load(String filename, Properties target) {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream(filename)) {
            if (is == null) throw new RuntimeException(
                    "No se encontró: " + filename);
            target.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando " + filename, e);
        }
    }

    // ── DB getters ──
    public String getDbUrl()      { return dbProps.getProperty("db.url"); }
    public String getDbUser()     { return dbProps.getProperty("db.user"); }
    public String getDbPassword() { return dbProps.getProperty("db.password"); }
    public String getDbDriver()   { return dbProps.getProperty("db.driver"); }

    // ── App getters ──
    public String getAppName()    { return appProps.getProperty("app.name"); }
    public String getViewType()   { return appProps.getProperty("view.type", "console"); }
}


// ─────────────────────────────────────────────────────────────
// 2. DB / INFRAESTRUCTURA
// ─────────────────────────────────────────────────────────────

// ── com/app/db/ConnectionManager.java ──
package com.app.db;

import com.app.config.AppConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton para gestión de conexiones JDBC.
 * En proyectos reales se reemplaza por un pool (HikariCP, c3p0...).
 */
public class ConnectionManager {

    private static ConnectionManager instance;
    private final AppConfig config = AppConfig.getInstance();

    private ConnectionManager() {
        try {
            Class.forName(config.getDbDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC no encontrado", e);
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) instance = new ConnectionManager();
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                config.getDbUrl(),
                config.getDbUser(),
                config.getDbPassword());
    }
}


// ─────────────────────────────────────────────────────────────
// 3. MODEL LAYER (entidades)
// ─────────────────────────────────────────────────────────────

// ── com/app/model/entity/Usuario.java ──
package com.app.model.entity;

public class Usuario {

    private int    id;
    private String nombre;
    private String email;

    public Usuario() {}
    public Usuario(int id, String nombre, String email) {
        this.id     = id;
        this.nombre = nombre;
        this.email  = email;
    }

    // Getters / Setters
    public int    getId()    { return id; }
    public String getNombre(){ return nombre; }
    public String getEmail() { return email; }
    public void   setId(int id)           { this.id     = id; }
    public void   setNombre(String nombre){ this.nombre = nombre; }
    public void   setEmail(String email)  { this.email  = email; }

    @Override
    public String toString() {
        return String.format("Usuario{id=%d, nombre='%s', email='%s'}",
                id, nombre, email);
    }
}


// ─────────────────────────────────────────────────────────────
// 4. DAO LAYER — interfaces genéricas
// ─────────────────────────────────────────────────────────────

// ── com/app/dao/GenericDAO.java ──
package com.app.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica DAO.
 * T  = tipo de entidad  (ej. Usuario)
 * ID = tipo de la clave (ej. Integer)
 */
public interface GenericDAO<T, ID> {
    T              save(T entity);
    Optional<T>    findById(ID id);
    List<T>        findAll();
    boolean        update(T entity);
    boolean        deleteById(ID id);
}


// ── com/app/dao/UsuarioDAO.java ──
package com.app.dao;

import com.app.model.entity.Usuario;
import java.util.List;

/**
 * Extiende GenericDAO añadiendo búsquedas específicas de Usuario.
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Integer> {
    List<Usuario> findByNombre(String nombre);
    boolean       existsByEmail(String email);
}


// ─────────────────────────────────────────────────────────────
// 5. DAO LAYER — implementaciones
// ─────────────────────────────────────────────────────────────

// ── com/app/dao/impl/GenericDAOImpl.java ──
package com.app.dao.impl;

import com.app.dao.GenericDAO;
import com.app.db.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación abstracta y genérica del patrón DAO.
 * Las subclases solo implementan:
 *   - mapRow(ResultSet)  → cómo convertir una fila en objeto T
 *   - las queries SQL propias de la entidad
 */
public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    protected final ConnectionManager cm = ConnectionManager.getInstance();

    // ── Métodos abstractos que cada subclase concreta define ──
    protected abstract T           mapRow(ResultSet rs) throws SQLException;
    protected abstract String      getInsertSQL();
    protected abstract String      getUpdateSQL();
    protected abstract String      getDeleteSQL();
    protected abstract String      getFindByIdSQL();
    protected abstract String      getFindAllSQL();
    protected abstract void        setInsertParams(PreparedStatement ps, T entity) throws SQLException;
    protected abstract void        setUpdateParams(PreparedStatement ps, T entity) throws SQLException;
    protected abstract void        setDeleteParam(PreparedStatement ps, ID id)     throws SQLException;
    protected abstract void        setFindByIdParam(PreparedStatement ps, ID id)   throws SQLException;

    // ── Operaciones CRUD genéricas ──

    @Override
    public Optional<T> findById(ID id) {
        try (Connection conn = cm.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindByIdSQL())) {

            setFindByIdParam(ps, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en findById", e);
        }
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        try (Connection conn = cm.getConnection();
             PreparedStatement ps = conn.prepareStatement(getFindAllSQL());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Error en findAll", e);
        }
        return list;
    }

    @Override
    public boolean update(T entity) {
        try (Connection conn = cm.getConnection();
             PreparedStatement ps = conn.prepareStatement(getUpdateSQL())) {

            setUpdateParams(ps, entity);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error en update", e);
        }
    }

    @Override
    public boolean deleteById(ID id) {
        try (Connection conn = cm.getConnection();
             PreparedStatement ps = conn.prepareStatement(getDeleteSQL())) {

            setDeleteParam(ps, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error en deleteById", e);
        }
    }
}


// ── com/app/dao/impl/UsuarioDAOImpl.java ──
package com.app.dao.impl;

import com.app.dao.UsuarioDAO;
import com.app.model.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer>
        implements UsuarioDAO {

    // ── SQL ──
    private static final String INSERT     = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
    private static final String UPDATE     = "UPDATE usuarios SET nombre=?, email=? WHERE id=?";
    private static final String DELETE     = "DELETE FROM usuarios WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM usuarios WHERE id=?";
    private static final String FIND_ALL   = "SELECT * FROM usuarios";
    private static final String FIND_BY_NAME= "SELECT * FROM usuarios WHERE nombre LIKE ?";
    private static final String EXISTS_EMAIL= "SELECT COUNT(*) FROM usuarios WHERE email=?";

    // ── Mapeo ResultSet → Entidad ──
    @Override
    protected Usuario mapRow(ResultSet rs) throws SQLException {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("email")
        );
    }

    // ── Parámetros SQL ──
    @Override protected String getInsertSQL()   { return INSERT; }
    @Override protected String getUpdateSQL()   { return UPDATE; }
    @Override protected String getDeleteSQL()   { return DELETE; }
    @Override protected String getFindByIdSQL() { return FIND_BY_ID; }
    @Override protected String getFindAllSQL()  { return FIND_ALL; }

    @Override
    protected void setInsertParams(PreparedStatement ps, Usuario u) throws SQLException {
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getEmail());
    }
    @Override
    protected void setUpdateParams(PreparedStatement ps, Usuario u) throws SQLException {
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getEmail());
        ps.setInt(3, u.getId());
    }
    @Override
    protected void setDeleteParam(PreparedStatement ps, Integer id) throws SQLException {
        ps.setInt(1, id);
    }
    @Override
    protected void setFindByIdParam(PreparedStatement ps, Integer id) throws SQLException {
        ps.setInt(1, id);
    }

    // ── save devuelve el objeto con el ID generado ──
    @Override
    public Usuario save(Usuario u) {
        try (Connection conn = cm.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     INSERT, Statement.RETURN_GENERATED_KEYS)) {

            setInsertParams(ps, u);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) u.setId(keys.getInt(1));
            }
            return u;
        } catch (SQLException e) {
            throw new RuntimeException("Error en save(Usuario)", e);
        }
    }

    // ── Métodos específicos de UsuarioDAO ──
    @Override
    public List<Usuario> findByNombre(String nombre) {
        List<Usuario> list = new ArrayList<>();
        try (Connection conn = cm.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_NAME)) {

            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en findByNombre", e);
        }
        return list;
    }

    @Override
    public boolean existsByEmail(String email) {
        try (Connection conn = cm.getConnection();
             PreparedStatement ps = conn.prepareStatement(EXISTS_EMAIL)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en existsByEmail", e);
        }
    }
}


// ─────────────────────────────────────────────────────────────
// 6. VIEW LAYER — interfaz + abstract + implementaciones
// ─────────────────────────────────────────────────────────────

// ── com/app/view/View.java ──
package com.app.view;

import com.app.model.entity.Usuario;
import java.util.List;

/**
 * Contrato que toda vista debe cumplir.
 * El Controller solo conoce esta interfaz.
 */
public interface View {
    void       showMessage(String msg);
    void       showError(String msg);
    void       showUsuarios(List<Usuario> usuarios);
    void       showUsuario(Usuario usuario);
    String     askInput(String prompt);
    boolean    confirm(String question);
    void       showMenu(String[] options, String title);
    int        getMenuChoice();
}


// ── com/app/view/BaseView.java ──
package com.app.view;

import com.app.model.entity.Usuario;
import java.util.List;

/**
 * Comportamiento común a todas las vistas.
 * Evita duplicar lógica de formato entre ConsoleView y SwingView.
 */
public abstract class BaseView implements View {

    // ── Formato compartido para la lista de usuarios ──
    protected String formatUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) return "(Sin usuarios)";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-20s %-30s%n", "ID", "NOMBRE", "EMAIL"));
        sb.append("─".repeat(55)).append("\n");
        for (Usuario u : usuarios) {
            sb.append(String.format("%-5d %-20s %-30s%n",
                    u.getId(), u.getNombre(), u.getEmail()));
        }
        return sb.toString();
    }

    protected String formatUsuario(Usuario u) {
        return String.format("ID: %d%nNombre: %s%nEmail: %s",
                u.getId(), u.getNombre(), u.getEmail());
    }

    protected String buildMenu(String[] options, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══ ").append(title).append(" ═══╗\n");
        for (int i = 0; i < options.length; i++) {
            sb.append(String.format("  %d. %s%n", i + 1, options[i]));
        }
        sb.append("╚" + "═".repeat(title.length() + 8) + "╝");
        return sb.toString();
    }
}


// ── com/app/view/ConsoleView.java ──
package com.app.view;

import com.app.model.entity.Usuario;
import java.util.List;
import java.util.Scanner;

/**
 * Vista de consola (System.in / System.out).
 */
public class ConsoleView extends BaseView {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMessage(String msg) {
        System.out.println("[INFO] " + msg);
    }

    @Override
    public void showError(String msg) {
        System.err.println("[ERROR] " + msg);
    }

    @Override
    public void showUsuarios(List<Usuario> usuarios) {
        System.out.println("\n" + formatUsuarios(usuarios));
    }

    @Override
    public void showUsuario(Usuario usuario) {
        System.out.println("\n" + formatUsuario(usuario));
    }

    @Override
    public String askInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    @Override
    public boolean confirm(String question) {
        System.out.print(question + " (s/n): ");
        String r = scanner.nextLine().trim().toLowerCase();
        return r.equals("s") || r.equals("si") || r.equals("sí");
    }

    @Override
    public void showMenu(String[] options, String title) {
        System.out.println("\n" + buildMenu(options, title));
    }

    @Override
    public int getMenuChoice() {
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}


// ── com/app/view/SwingView.java ──
package com.app.view;

import com.app.model.entity.Usuario;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Vista basada en JOptionPane (Swing).
 * Reutiliza la lógica de formato de BaseView.
 */
public class SwingView extends BaseView {

    @Override
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showUsuarios(List<Usuario> usuarios) {
        JOptionPane.showMessageDialog(null,
                formatUsuarios(usuarios), "Lista de Usuarios",
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void showUsuario(Usuario usuario) {
        JOptionPane.showMessageDialog(null,
                formatUsuario(usuario), "Detalle de Usuario",
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public String askInput(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    @Override
    public boolean confirm(String question) {
        int r = JOptionPane.showConfirmDialog(null, question,
                "Confirmar", JOptionPane.YES_NO_OPTION);
        return r == JOptionPane.YES_OPTION;
    }

    @Override
    public void showMenu(String[] options, String title) {
        // El menú en Swing se muestra en getMenuChoice()
        // Se almacena internamente para que getMenuChoice lo use
    }

    @Override
    public int getMenuChoice() {
        // Construye el menú como lista de selección
        String[] opts = {"Listar usuarios", "Buscar por ID",
                "Crear usuario", "Actualizar usuario",
                "Eliminar usuario", "Salir"};
        Object sel = JOptionPane.showInputDialog(
                null, "Selecciona una opción:", "Menú",
                JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);
        if (sel == null) return 6; // Salir
        for (int i = 0; i < opts.length; i++) {
            if (opts[i].equals(sel)) return i + 1;
        }
        return -1;
    }
}


// ─────────────────────────────────────────────────────────────
// 7. CONTROLLER LAYER
// ─────────────────────────────────────────────────────────────

// ── com/app/controller/UsuarioController.java ──
package com.app.controller;

import com.app.dao.UsuarioDAO;
import com.app.model.entity.Usuario;
import com.app.view.View;

import java.util.List;
import java.util.Optional;

/**
 * Controlador de usuarios.
 * Recibe eventos de la Vista, orquesta el DAO y devuelve resultados a la Vista.
 * No sabe si la Vista es consola o Swing → depende de la interfaz View.
 */
public class UsuarioController {

    private final View       view;
    private final UsuarioDAO usuarioDAO;

    // Inyección de dependencias por constructor
    public UsuarioController(View view, UsuarioDAO usuarioDAO) {
        this.view       = view;
        this.usuarioDAO = usuarioDAO;
    }

    // ── Menú principal ──
    public void run() {
        String[] menuOptions = {
            "Listar todos", "Buscar por ID", "Crear usuario",
            "Actualizar usuario", "Eliminar usuario", "Salir"
        };

        boolean running = true;
        while (running) {
            view.showMenu(menuOptions, "Gestión de Usuarios");
            int choice = view.getMenuChoice();

            switch (choice) {
                case 1 -> listarTodos();
                case 2 -> buscarPorId();
                case 3 -> crearUsuario();
                case 4 -> actualizarUsuario();
                case 5 -> eliminarUsuario();
                case 6 -> running = false;
                default -> view.showError("Opción no válida");
            }
        }
        view.showMessage("¡Hasta luego!");
    }

    // ── Operaciones CRUD ──

    public void listarTodos() {
        List<Usuario> usuarios = usuarioDAO.findAll();
        if (usuarios.isEmpty()) {
            view.showMessage("No hay usuarios registrados.");
        } else {
            view.showUsuarios(usuarios);
        }
    }

    public void buscarPorId() {
        String input = view.askInput("ID del usuario");
        try {
            int id = Integer.parseInt(input);
            Optional<Usuario> usuario = usuarioDAO.findById(id);
            if (usuario.isPresent()) {
                view.showUsuario(usuario.get());
            } else {
                view.showError("No se encontró usuario con ID " + id);
            }
        } catch (NumberFormatException e) {
            view.showError("ID inválido: " + input);
        }
    }

    public void crearUsuario() {
        String nombre = view.askInput("Nombre del usuario");
        String email  = view.askInput("Email del usuario");

        if (nombre.isBlank() || email.isBlank()) {
            view.showError("Nombre y email son requeridos.");
            return;
        }
        if (usuarioDAO.existsByEmail(email)) {
            view.showError("Ya existe un usuario con ese email.");
            return;
        }

        Usuario nuevo = new Usuario(0, nombre, email);
        usuarioDAO.save(nuevo);
        view.showMessage("Usuario creado con ID: " + nuevo.getId());
    }

    public void actualizarUsuario() {
        String input = view.askInput("ID del usuario a actualizar");
        try {
            int id = Integer.parseInt(input);
            Optional<Usuario> opt = usuarioDAO.findById(id);
            if (opt.isEmpty()) {
                view.showError("No se encontró usuario con ID " + id);
                return;
            }
            Usuario u = opt.get();
            String nombre = view.askInput("Nuevo nombre [" + u.getNombre() + "]");
            String email  = view.askInput("Nuevo email ["  + u.getEmail()  + "]");

            if (!nombre.isBlank()) u.setNombre(nombre);
            if (!email.isBlank())  u.setEmail(email);

            boolean ok = usuarioDAO.update(u);
            view.showMessage(ok ? "Usuario actualizado." : "No se pudo actualizar.");
        } catch (NumberFormatException e) {
            view.showError("ID inválido.");
        }
    }

    public void eliminarUsuario() {
        String input = view.askInput("ID del usuario a eliminar");
        try {
            int id = Integer.parseInt(input);
            if (view.confirm("¿Confirmar eliminación del usuario " + id + "?")) {
                boolean ok = usuarioDAO.deleteById(id);
                view.showMessage(ok ? "Usuario eliminado." : "No se encontró el usuario.");
            }
        } catch (NumberFormatException e) {
            view.showError("ID inválido.");
        }
    }
}


// ─────────────────────────────────────────────────────────────
// 8. MAIN — punto de entrada + Factory de vistas
// ─────────────────────────────────────────────────────────────

// ── com/app/Main.java ──
package com.app;

import com.app.config.AppConfig;
import com.app.controller.UsuarioController;
import com.app.dao.UsuarioDAO;
import com.app.dao.impl.UsuarioDAOImpl;
import com.app.view.ConsoleView;
import com.app.view.SwingView;
import com.app.view.View;

public class Main {

    public static void main(String[] args) {

        AppConfig config = AppConfig.getInstance();

        // ── Factory: elige la vista según app.properties ──
        View view = createView(config.getViewType());

        // ── Inyección de dependencias ──
        UsuarioDAO          usuarioDAO  = new UsuarioDAOImpl();
        UsuarioController   controller  = new UsuarioController(view, usuarioDAO);

        view.showMessage("Bienvenido a " + config.getAppName());

        // ── Arrancar la aplicación ──
        controller.run();
    }

    private static View createView(String type) {
        return switch (type.toLowerCase()) {
            case "swing" -> new SwingView();
            default       -> new ConsoleView();
        };
    }
}


// ─────────────────────────────────────────────────────────────
// 9. SQL — Schema de base de datos
// ─────────────────────────────────────────────────────────────
/*
CREATE DATABASE IF NOT EXISTS appdb;
USE appdb;

CREATE TABLE usuarios (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nombre  VARCHAR(100) NOT NULL,
    email   VARCHAR(150) NOT NULL UNIQUE,
    INDEX idx_nombre (nombre)
);
*/


// ─────────────────────────────────────────────────────────────
// 10. pom.xml — Dependencias Maven
// ─────────────────────────────────────────────────────────────
/*
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.app</groupId>
  <artifactId>mvc-dao-jdbc</artifactId>
  <version>1.0.0</version>
  <packaging>jar</packaging>

  <properties>
    <java.version>17</java.version>
    <maven.compiler.source>${java.version}</maven.compiler.source>
    <maven.compiler.target>${java.version}</maven.compiler.target>
  </properties>

  <dependencies>
    <!-- MySQL JDBC Driver -->
    <dependency>
      <groupId>com.mysql</groupId>
      <artifactId>mysql-connector-j</artifactId>
      <version>8.3.0</version>
    </dependency>

    <!-- Tests -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.10.2</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
*/
