package app.repository.mappers;

import app.domain.Boleto;
import app.domain.Funcion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoletoRowMapper {

    public static Boleto mapRow(ResultSet rs, Funcion funcion) throws SQLException {
        Boleto boleto = new Boleto(
                rs.getInt("id"),
                rs.getString("codigo"),
                funcion,
                rs.getString("asiento")
        );
        boleto.setPrecio(rs.getDouble("precio"));
        return boleto;
    }
}
