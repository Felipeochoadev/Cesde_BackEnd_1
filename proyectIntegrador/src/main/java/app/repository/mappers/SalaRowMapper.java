package app.repository.mappers;

import app.domain.Sala;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaRowMapper {

    public static Sala mapRow(ResultSet rs) throws SQLException {
        return new Sala(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getInt("capacidad"),
                rs.getString("tipo"),
                rs.getDouble("recargo")
        );
    }
}
