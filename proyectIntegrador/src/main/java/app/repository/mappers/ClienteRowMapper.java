package app.repository.mappers;

import app.domain.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper {

    public static Cliente mapRow(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("telefono"),
                rs.getInt("edad")
        );
    }
}
