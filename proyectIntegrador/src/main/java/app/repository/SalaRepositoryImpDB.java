package app.repository;

import app.domain.Sala;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.SalaRowMapper;
import app.service.ouputports.SalaRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaRepositoryImpDB implements SalaRepositoryPort {

    private int obtenerOInsertarTipoSalaId(Connection conn, String tipoNombre, double recargo) throws SQLException {
        if (tipoNombre == null || tipoNombre.trim().isEmpty()) {
            return 1;
        }
        String sqlSelect = "SELECT id FROM tipos_sala WHERE LOWER(descripcion) = LOWER(?) OR LOWER(codigo) = LOWER(?);";
        try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
            ps.setString(1, tipoNombre.trim());
            ps.setString(2, tipoNombre.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }

        String sqlInsert = "INSERT INTO tipos_sala (codigo, descripcion, recargo) VALUES (?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            String codigo = tipoNombre.trim().toUpperCase().replace(" ", "_");
            ps.setString(1, codigo);
            ps.setString(2, tipoNombre.trim());
            ps.setDouble(3, recargo);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 1;
    }

    @Override
    public Sala create(Sala sala) {
        String sql = "INSERT INTO salas (nombre, capacidad, tipo_sala_id) VALUES (?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int tipoSalaId = obtenerOInsertarTipoSalaId(conn, sala.getTipo(), sala.getRecargo());

            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, sala.getNombre());
                ps.setInt(2, sala.getCapacidad());
                ps.setInt(3, tipoSalaId);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        sala.setId(rs.getInt(1));
                    }
                }
            }
            return sala;

        } catch (SQLException e) {
            System.err.println("Error al registrar sala en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Sala getById(int id) {
        String sql = "SELECT s.id, s.nombre, s.capacidad, ts.descripcion AS tipo, ts.recargo "
                   + "FROM salas s "
                   + "INNER JOIN tipos_sala ts ON s.tipo_sala_id = ts.id "
                   + "WHERE s.id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return SalaRowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar sala por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Sala> getAll() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT s.id, s.nombre, s.capacidad, ts.descripcion AS tipo, ts.recargo "
                   + "FROM salas s "
                   + "INNER JOIN tipos_sala ts ON s.tipo_sala_id = ts.id "
                   + "ORDER BY s.id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                salas.add(SalaRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar salas desde BD: " + e.getMessage());
        }
        return salas;
    }

    @Override
    public Sala update(int id, Sala sala) {
        String sql = "UPDATE salas SET nombre = ?, capacidad = ?, tipo_sala_id = ? WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int tipoSalaId = obtenerOInsertarTipoSalaId(conn, sala.getTipo(), sala.getRecargo());

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, sala.getNombre());
                ps.setInt(2, sala.getCapacidad());
                ps.setInt(3, tipoSalaId);
                ps.setInt(4, id);
                ps.executeUpdate();
            }

            sala.setId(id);
            return sala;

        } catch (SQLException e) {
            System.err.println("Error al actualizar sala en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM salas WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar sala de BD: " + e.getMessage());
            return false;
        }
    }
}
