package app.repository.mappers;

import app.domain.Pelicula;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeliculaRowMapper {

    public static Pelicula mapRow(ResultSet rs) throws SQLException {
        return new Pelicula(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("genero"),
                rs.getInt("duracion_minutos"),
                rs.getDouble("precio_base"),
                rs.getInt("edad_minima")
        );
    }
}
