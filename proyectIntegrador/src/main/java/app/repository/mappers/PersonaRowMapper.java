package app.repository.mappers;

import app.domain.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaRowMapper {

    public static Persona mapRow(ResultSet rs) throws SQLException {
        return new Persona(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("telefono")
        );
    }
}
