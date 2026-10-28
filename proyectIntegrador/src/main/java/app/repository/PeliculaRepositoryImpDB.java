package app.repository;

import app.domain.Pelicula;
import app.repository.config.DatabaseConnection;
import app.repository.mappers.PeliculaRowMapper;
import app.service.ouputports.PeliculaRepositoryPort;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaRepositoryImpDB implements PeliculaRepositoryPort {

    private int obtenerOInsertarGeneroId(Connection conn, String generoNombre) throws SQLException {
        if (generoNombre == null || generoNombre.trim().isEmpty()) {
            return 1;
        }
        String sqlSelect = "SELECT id FROM generos_pelicula WHERE LOWER(descripcion) = LOWER(?) OR LOWER(codigo) = LOWER(?);";
        try (PreparedStatement ps = conn.prepareStatement(sqlSelect)) {
            ps.setString(1, generoNombre.trim());
            ps.setString(2, generoNombre.trim());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }

        String sqlInsert = "INSERT INTO generos_pelicula (codigo, descripcion) VALUES (?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            String codigo = generoNombre.trim().toUpperCase().replace(" ", "_");
            ps.setString(1, codigo);
            ps.setString(2, generoNombre.trim());
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
    public Pelicula create(Pelicula pelicula) {
        String sql = "INSERT INTO peliculas (titulo, genero_id, duracion_minutos, precio_base, edad_minima) "
                   + "VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int generoId = obtenerOInsertarGeneroId(conn, pelicula.getGenero());

            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, pelicula.getTitulo());
                ps.setInt(2, generoId);
                ps.setInt(3, pelicula.getDuracionMinutos());
                ps.setDouble(4, pelicula.getPrecioBase());
                ps.setInt(5, pelicula.getEdadMinima());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        pelicula.setId(rs.getInt(1));
                    }
                }
            }
            return pelicula;

        } catch (SQLException e) {
            System.err.println("Error al registrar película en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Pelicula getById(int id) {
        String sql = "SELECT p.id, p.titulo, gp.descripcion AS genero, p.duracion_minutos, p.precio_base, p.edad_minima "
                   + "FROM peliculas p "
                   + "INNER JOIN generos_pelicula gp ON p.genero_id = gp.id "
                   + "WHERE p.id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return PeliculaRowMapper.mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar película por ID en BD: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Pelicula> getAll() {
        List<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT p.id, p.titulo, gp.descripcion AS genero, p.duracion_minutos, p.precio_base, p.edad_minima "
                   + "FROM peliculas p "
                   + "INNER JOIN generos_pelicula gp ON p.genero_id = gp.id "
                   + "ORDER BY p.id ASC;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                peliculas.add(PeliculaRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar películas desde BD: " + e.getMessage());
        }
        return peliculas;
    }

    @Override
    public Pelicula update(int id, Pelicula pelicula) {
        String sql = "UPDATE peliculas SET titulo = ?, genero_id = ?, duracion_minutos = ?, precio_base = ?, edad_minima = ? "
                   + "WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection()) {
            int generoId = obtenerOInsertarGeneroId(conn, pelicula.getGenero());

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, pelicula.getTitulo());
                ps.setInt(2, generoId);
                ps.setInt(3, pelicula.getDuracionMinutos());
                ps.setDouble(4, pelicula.getPrecioBase());
                ps.setInt(5, pelicula.getEdadMinima());
                ps.setInt(6, id);
                ps.executeUpdate();
            }

            pelicula.setId(id);
            return pelicula;

        } catch (SQLException e) {
            System.err.println("Error al actualizar película en BD: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM peliculas WHERE id = ?;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar película de BD: " + e.getMessage());
            return false;
        }
    }
}
