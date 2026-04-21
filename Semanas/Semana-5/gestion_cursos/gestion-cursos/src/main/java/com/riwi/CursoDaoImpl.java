package com.riwi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CursoDaoImpl implements CursoDAO{
    @Override
    public List<Curso> findAll() {
        return List.of();
    }

    @Override
    public Curso save(Curso curso) {
        String sql = "INSERT INTO cursos (nombre, instructor, duracion_horas) VALUES (?, ?, ?)";

        try(Connection cn = DatabaseConnection.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){

                ps.setString(1, curso.getNombre());
                ps.setString(2, curso.getInstructor());
                ps.setInt(3, curso.getDuracionHoras());

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    // 2. Obtenemos el conjunto de claves generadas
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        System.out.println("ResultSet:" + rs.toString());
                        if (rs.next()) {
                            // 3. Asignamos el ID al objeto curso
                            int idGenerado = rs.getInt(1);
                            curso.setId(idGenerado);
                        }
                    }
                }

            return curso;
        }catch (SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
            return null;
        }
    }

    @Override
    public Curso update(Curso curso) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
