package app.repository.mappers;

import app.domain.Cliente;
import app.domain.Funcion;
import app.domain.Reserva;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaRowMapper {

    public static Reserva mapRow(ResultSet rs, Cliente cliente, Funcion funcion) throws SQLException {
        Reserva reserva = new Reserva(
                rs.getInt("id"),
                cliente,
                funcion,
                rs.getInt("cantidad_boletos")
        );
        reserva.setEstado(rs.getString("estado"));
        return reserva;
    }
}
