package app.repository.mappers;

import app.domain.Factura;
import app.domain.Reserva;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaRowMapper {

    public static Factura mapRow(ResultSet rs, Reserva reserva) throws SQLException {
        Factura factura = new Factura(
                rs.getInt("id"),
                rs.getString("numero"),
                reserva,
                rs.getString("metodo_pago")
        );
        factura.setPagada(rs.getBoolean("pagada"));
        return factura;
    }
}
