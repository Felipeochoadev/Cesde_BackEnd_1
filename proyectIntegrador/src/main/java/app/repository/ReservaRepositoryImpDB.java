package app.repository;

import app.domain.Cliente;
import app.domain.Funcion;
import app.domain.Reserva;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.ReservaRowMapper;
import app.service.ouputports.ClienteRepositoryPort;
import app.service.ouputports.FuncionRepositoryPort;
import app.service.ouputports.ReservaRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryImpDB implements ReservaRepositoryPort {

    private final ClienteRepositoryPort clienteRepository = new ClienteRepositoryImpDB();
    private final FuncionRepositoryPort funcionRepository = new FuncionRepositoryImpDB();

    private int obtenerOInsertarEstadoId(Connection conn, String estadoNombre) throws SQLException {
        if (estadoNombre == null || estadoNombre.trim().isEmpty()) {
            return 1;
        }
        String sqlSelect = "SELECT id FROM estados_reserva WHERE LOWER(descripcion) = LOWER(?) OR LOWER(codigo) = LOWER(?);";
        try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
            ps.setString(1, estadoNombre.trim());
            ps.setString(2, estadoNombre.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return 1;
    }

    @Override
    public Reserva create(Reserva reserva) {
        String sql = "INSERT INTO reservas (cliente_id, funcion_id, cantidad_boletos, estado_id) VALUES (?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int estadoId = obtenerOInsertarEstadoId(conn, reserva.getEstado());

            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                int clienteId = (reserva.getCliente() != null) ? reserva.getCliente().getId() : 1;
                int funcionId = (reserva.getFuncion() != null) ? reserva.getFuncion().getId() : 1;

                ps.setInt(1, clienteId);
                ps.setInt(2, funcionId);
                ps.setInt(3, reserva.getCantidadBoletos());
                ps.setInt(4, estadoId);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        reserva.setId(rs.getInt(1));
                    }
                }
            }
            return reserva;

        } catch (SQLException e) {
            System.err.println("Error al registrar reserva en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Reserva getById(int id) {
        String sql = "SELECT reserva_id AS id, cliente_id, funcion_id, cantidad_boletos, estado "
                   + "FROM dbo.vw_reservas_detalle "
                   + "WHERE reserva_id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = clienteRepository.getById(rs.getInt("cliente_id"));
                    Funcion funcion = funcionRepository.getById(rs.getInt("funcion_id"));
                    return ReservaRowMapper.mapRow(rs, cliente, funcion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar reserva por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reserva> getAll() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT reserva_id AS id, cliente_id, funcion_id, cantidad_boletos, estado "
                   + "FROM dbo.vw_reservas_detalle "
                   + "ORDER BY reserva_id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = clienteRepository.getById(rs.getInt("cliente_id"));
                Funcion funcion = funcionRepository.getById(rs.getInt("funcion_id"));
                reservas.add(ReservaRowMapper.mapRow(rs, cliente, funcion));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar reservas desde BD: " + e.getMessage());
        }
        return reservas;
    }

    @Override
    public Reserva update(int id, Reserva reserva) {
        String sql = "UPDATE reservas SET cliente_id = ?, funcion_id = ?, cantidad_boletos = ?, estado_id = ? WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int estadoId = obtenerOInsertarEstadoId(conn, reserva.getEstado());

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int clienteId = (reserva.getCliente() != null) ? reserva.getCliente().getId() : 1;
                int funcionId = (reserva.getFuncion() != null) ? reserva.getFuncion().getId() : 1;

                ps.setInt(1, clienteId);
                ps.setInt(2, funcionId);
                ps.setInt(3, reserva.getCantidadBoletos());
                ps.setInt(4, estadoId);
                ps.setInt(5, id);
                ps.executeUpdate();
            }

            reserva.setId(id);
            return reserva;

        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM reservas WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva de BD: " + e.getMessage());
            return false;
        }
    }
}
