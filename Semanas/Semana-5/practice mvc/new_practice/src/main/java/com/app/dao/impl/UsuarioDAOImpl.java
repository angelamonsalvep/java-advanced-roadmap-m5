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