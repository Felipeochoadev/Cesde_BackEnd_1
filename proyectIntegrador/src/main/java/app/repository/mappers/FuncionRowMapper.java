package app.repository.mappers;

import app.domain.Funcion;
import app.domain.Pelicula;
import app.domain.Sala;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionRowMapper {

    public static Funcion mapRow(ResultSet rs) throws SQLException {
        Pelicula pelicula = new Pelicula(
                rs.getInt("p_id"),
                rs.getString("titulo"),
                rs.getString("p_genero"),
                rs.getInt("duracion_minutos"),
                rs.getDouble("precio_base"),
                rs.getInt("edad_minima")
        );

        Sala sala = new Sala(
                rs.getInt("s_id"),
                rs.getString("s_nombre"),
                rs.getInt("s_capacidad"),
                rs.getString("s_tipo"),
                rs.getDouble("s_recargo")
        );

        return new Funcion(
                rs.getInt("id"),
                pelicula,
                sala,
                rs.getString("fecha"),
                rs.getString("hora")
        );
    }
}
