package app.repository;

import app.domain.Boleto;
import app.domain.Funcion;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.BoletoRowMapper;
import app.service.ouputports.BoletoRepositoryPort;
import app.service.ouputports.FuncionRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoletoRepositoryImpDB implements BoletoRepositoryPort {

    private final FuncionRepositoryPort funcionRepository = new FuncionRepositoryImpDB();

    @Override
    public Boleto create(Boleto boleto) {
        String sql = "INSERT INTO boletos (codigo, funcion_id, asiento, precio) VALUES (?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            int funcionId = (boleto.getFuncion() != null) ? boleto.getFuncion().getId() : 1;

            ps.setString(1, boleto.getCodigo());
            ps.setInt(2, funcionId);
            ps.setString(3, boleto.getAsiento());
            ps.setDouble(4, boleto.getPrecio());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    boleto.setId(rs.getInt(1));
                }
            }
            return boleto;

        } catch (SQLException e) {
            System.err.println("Error al emitir boleto en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Boleto getById(int id) {
        String sql = "SELECT id, codigo, funcion_id, asiento, precio FROM boletos WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Funcion funcion = funcionRepository.getById(rs.getInt("funcion_id"));
                    return BoletoRowMapper.mapRow(rs, funcion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar boleto por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Boleto> getAll() {
        List<Boleto> boletos = new ArrayList<>();
        String sql = "SELECT id, codigo, funcion_id, asiento, precio FROM boletos ORDER BY id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Funcion funcion = funcionRepository.getById(rs.getInt("funcion_id"));
                boletos.add(BoletoRowMapper.mapRow(rs, funcion));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar boletos desde BD: " + e.getMessage());
        }
        return boletos;
    }

    @Override
    public Boleto update(int id, Boleto boleto) {
        String sql = "UPDATE boletos SET codigo = ?, funcion_id = ?, asiento = ?, precio = ? WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int funcionId = (boleto.getFuncion() != null) ? boleto.getFuncion().getId() : 1;

            ps.setString(1, boleto.getCodigo());
            ps.setInt(2, funcionId);
            ps.setString(3, boleto.getAsiento());
            ps.setDouble(4, boleto.getPrecio());
            ps.setInt(5, id);
            ps.executeUpdate();

            boleto.setId(id);
            return boleto;

        } catch (SQLException e) {
            System.err.println("Error al actualizar boleto en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM boletos WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar boleto de BD: " + e.getMessage());
            return false;
        }
    }
}
