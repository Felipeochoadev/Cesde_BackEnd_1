package app.repository;

import app.domain.Funcion;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.FuncionRowMapper;
import app.service.ouputports.FuncionRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionRepositoryImpDB implements FuncionRepositoryPort {

    private static final String BASE_SELECT = 
            "SELECT funcion_id AS id, p_id, titulo, p_genero, duracion_minutos, precio_base, edad_minima, "
            + "s_id, s_nombre, s_capacidad, s_tipo, s_recargo, fecha, hora "
            + "FROM dbo.vw_funciones_cartelera ";

    @Override
    public Funcion create(Funcion funcion) {
        String sql = "INSERT INTO funciones (pelicula_id, sala_id, fecha, hora) VALUES (?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            int peliculaId = (funcion.getPelicula() != null) ? funcion.getPelicula().getId() : 1;
            int salaId = (funcion.getSala() != null) ? funcion.getSala().getId() : 1;

            ps.setInt(1, peliculaId);
            ps.setInt(2, salaId);
            ps.setString(3, funcion.getFecha());
            ps.setString(4, funcion.getHora());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    funcion.setId(rs.getInt(1));
                }
            }
            return funcion;

        } catch (SQLException e) {
            System.err.println("Error al programar función en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Funcion getById(int id) {
        String sql = BASE_SELECT + "WHERE funcion_id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return FuncionRowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar función por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Funcion> getAll() {
        List<Funcion> funciones = new ArrayList<>();
        String sql = BASE_SELECT + "ORDER BY funcion_id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                funciones.add(FuncionRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar funciones desde BD: " + e.getMessage());
        }
        return funciones;
    }

    @Override
    public Funcion update(int id, Funcion funcion) {
        String sql = "UPDATE funciones SET pelicula_id = ?, sala_id = ?, fecha = ?, hora = ? WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int peliculaId = (funcion.getPelicula() != null) ? funcion.getPelicula().getId() : 1;
            int salaId = (funcion.getSala() != null) ? funcion.getSala().getId() : 1;

            ps.setInt(1, peliculaId);
            ps.setInt(2, salaId);
            ps.setString(3, funcion.getFecha());
            ps.setString(4, funcion.getHora());
            ps.setInt(5, id);
            ps.executeUpdate();

            funcion.setId(id);
            return funcion;

        } catch (SQLException e) {
            System.err.println("Error al actualizar función en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM funciones WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar función de BD: " + e.getMessage());
            return false;
        }
    }
}
