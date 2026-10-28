package app.repository;

import app.domain.Factura;
import app.domain.Reserva;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.FacturaRowMapper;
import app.service.ouputports.FacturaRepositoryPort;
import app.service.ouputports.ReservaRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepositoryImpDB implements FacturaRepositoryPort {

    private final ReservaRepositoryPort reservaRepository = new ReservaRepositoryImpDB();

    private int obtenerOInsertarMetodoPagoId(Connection conn, String metodoPagoNombre) throws SQLException {
        if (metodoPagoNombre == null || metodoPagoNombre.trim().isEmpty()) {
            return 1;
        }
        String sqlSelect = "SELECT id FROM metodos_pago WHERE LOWER(nombre) = LOWER(?) OR LOWER(codigo) = LOWER(?);";
        try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
            ps.setString(1, metodoPagoNombre.trim());
            ps.setString(2, metodoPagoNombre.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return 1;
    }

    @Override
    public Factura create(Factura factura) {
        String sql = "INSERT INTO facturas (numero, reserva_id, metodo_pago_id, pagada) VALUES (?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int metodoPagoId = obtenerOInsertarMetodoPagoId(conn, factura.getMetodoPago());

            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                int reservaId = (factura.getReserva() != null) ? factura.getReserva().getId() : 1;

                ps.setString(1, factura.getNumero());
                ps.setInt(2, reservaId);
                ps.setInt(3, metodoPagoId);
                ps.setBoolean(4, factura.isPagada());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        factura.setId(rs.getInt(1));
                    }
                }
            }
            return factura;

        } catch (SQLException e) {
            System.err.println("Error al registrar factura en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Factura getById(int id) {
        String sql = "SELECT factura_id AS id, numero, reserva_id, metodo_pago, pagada "
                   + "FROM dbo.vw_facturas_detalle "
                   + "WHERE factura_id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Reserva reserva = reservaRepository.getById(rs.getInt("reserva_id"));
                    return FacturaRowMapper.mapRow(rs, reserva);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar factura por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Factura> getAll() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT factura_id AS id, numero, reserva_id, metodo_pago, pagada "
                   + "FROM dbo.vw_facturas_detalle "
                   + "ORDER BY factura_id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = reservaRepository.getById(rs.getInt("reserva_id"));
                facturas.add(FacturaRowMapper.mapRow(rs, reserva));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar facturas desde BD: " + e.getMessage());
        }
        return facturas;
    }

    @Override
    public Factura update(int id, Factura factura) {
        String sql = "UPDATE facturas SET numero = ?, reserva_id = ?, metodo_pago_id = ?, pagada = ? WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int metodoPagoId = obtenerOInsertarMetodoPagoId(conn, factura.getMetodoPago());

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                int reservaId = (factura.getReserva() != null) ? factura.getReserva().getId() : 1;

                ps.setString(1, factura.getNumero());
                ps.setInt(2, reservaId);
                ps.setInt(3, metodoPagoId);
                ps.setBoolean(4, factura.isPagada());
                ps.setInt(5, id);
                ps.executeUpdate();
            }

            factura.setId(id);
            return factura;

        } catch (SQLException e) {
            System.err.println("Error al actualizar factura en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM facturas WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar factura de BD: " + e.getMessage());
            return false;
        }
    }
}
