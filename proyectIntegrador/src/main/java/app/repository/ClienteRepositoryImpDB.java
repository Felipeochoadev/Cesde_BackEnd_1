package app.repository;

import app.domain.Cliente;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.ClienteRowMapper;
import app.service.ouputports.ClienteRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpDB implements ClienteRepositoryPort {

    @Override
    public Cliente create(Cliente cliente) {
        String sqlPersona = "INSERT INTO personas (nombre, correo, telefono) VALUES (?, ?, ?);";
        String sqlCliente = "INSERT INTO clientes (id, edad) VALUES (?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            int generatedId = 0;
            try (PreparedStatement psPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                psPersona.setString(1, cliente.getNombre());
                psPersona.setString(2, cliente.getCorreo());
                psPersona.setString(3, cliente.getTelefono());
                psPersona.executeUpdate();

                try (ResultSet rs = psPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }

            if (generatedId == 0) {
                conn.rollback();
                throw new SQLException("No se pudo obtener el ID autoincremental generado para la persona.");
            }

            try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
                psCliente.setInt(1, generatedId);
                psCliente.setInt(2, cliente.getEdad());
                psCliente.executeUpdate();
            }

            conn.commit();
            cliente.setId(generatedId);
            return cliente;

        } catch (SQLException e) {
            System.err.println("Error al registrar cliente en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Cliente getById(int id) {
        String sql = "SELECT cliente_id AS id, nombre, correo, telefono, edad "
                   + "FROM dbo.vw_clientes_detalle "
                   + "WHERE cliente_id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return ClienteRowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar cliente por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT cliente_id AS id, nombre, correo, telefono, edad "
                   + "FROM dbo.vw_clientes_detalle "
                   + "ORDER BY cliente_id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clientes.add(ClienteRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes desde BD: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public Cliente update(int id, Cliente cliente) {
        String sqlPersona = "UPDATE personas SET nombre = ?, correo = ?, telefono = ? WHERE id = ?;";
        String sqlCliente = "UPDATE clientes SET edad = ? WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psPersona = conn.prepareStatement(sqlPersona)) {
                psPersona.setString(1, cliente.getNombre());
                psPersona.setString(2, cliente.getCorreo());
                psPersona.setString(3, cliente.getTelefono());
                psPersona.setInt(4, id);
                psPersona.executeUpdate();
            }

            try (PreparedStatement psCliente = conn.prepareStatement(sqlCliente)) {
                psCliente.setInt(1, cliente.getEdad());
                psCliente.setInt(2, id);
                psCliente.executeUpdate();
            }

            conn.commit();
            cliente.setId(id);
            return cliente;

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM personas WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente de BD: " + e.getMessage());
            return false;
        }
    }
}
