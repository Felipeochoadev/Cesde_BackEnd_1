package app.repository;

import app.domain.Persona;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.PersonaRowMapper;
import app.service.ouputports.PersonaRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepositoryImpDB implements PersonaRepositoryPort {

    @Override
    public Persona create(Persona persona) {
        String sql = "INSERT INTO personas (nombre, correo, telefono) VALUES (?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getCorreo());
            ps.setString(3, persona.getTelefono());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    persona.setId(rs.getInt(1));
                }
            }
            return persona;

        } catch (SQLException e) {
            System.err.println("Error al registrar persona en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Persona getById(int id) {
        String sql = "SELECT id, nombre, correo, telefono FROM personas WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return PersonaRowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar persona por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Persona> getAll() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT id, nombre, correo, telefono FROM personas ORDER BY id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                personas.add(PersonaRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar personas desde BD: " + e.getMessage());
        }
        return personas;
    }

    @Override
    public Persona update(int id, Persona persona) {
        String sql = "UPDATE personas SET nombre = ?, correo = ?, telefono = ? WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getCorreo());
            ps.setString(3, persona.getTelefono());
            ps.setInt(4, id);
            ps.executeUpdate();

            persona.setId(id);
            return persona;

        } catch (SQLException e) {
            System.err.println("Error al actualizar persona en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM personas WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar persona de BD: " + e.getMessage());
            return false;
        }
    }
}
